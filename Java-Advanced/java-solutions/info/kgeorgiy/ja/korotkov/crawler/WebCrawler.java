package info.kgeorgiy.ja.korotkov.crawler;

import info.kgeorgiy.java.advanced.crawler.*;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;


/**
 * Implementation of {@link Crawler} interface.
 *
 * @author Arsentiy Korotkov
 */
public class WebCrawler implements Crawler {
    private final Downloader downloader;
    private final ExecutorService downloaders;
    private final ExecutorService extractors;
    private final int perHost;

    private final static long TIMEOUT = 1;
    private final static double TIME_SCALE = 1;
    private final static TimeUnit TIME_UNIT = TimeUnit.DAYS;

    /**
     * Creates crawler by a given args.
     *
     * @param downloader  allows you to download pages and extract links from them
     * @param downloaders maximum number of simultaneously loaded pages
     * @param extractors  the maximum number of pages from which links are extracted at the same time
     * @param perHost     the maximum number of pages loaded simultaneously from one host
     */
    public WebCrawler(final Downloader downloader, final int downloaders, final int extractors,
                      final int perHost) throws IllegalArgumentException {

        validate(downloader, downloaders, extractors, perHost);

        this.downloader = downloader;
        this.downloaders = Executors.newFixedThreadPool(downloaders);
        this.extractors = Executors.newFixedThreadPool(extractors);
        this.perHost = perHost;
    }

    private void validate(final Downloader downloader, final int downloaders, final int extractors,
                          final int perHost) throws IllegalArgumentException {

        if (downloader == null) {
            throw new IllegalArgumentException("Downloader cannot be null");
        }

        if (downloaders < 1) {
            throw new IllegalArgumentException("Downloaders must be natural");
        }

        if (extractors < 1) {
            throw new IllegalArgumentException("Extractors must be natural");
        }

        if (perHost < extractors) {
            throw new IllegalArgumentException("PerHost must bigger than extractors");
        }
    }

    /**
     * Downloads website up to specified depth.
     *
     * @param url   start <a href="http://tools.ietf.org/html/rfc3986">URL</a>.
     * @param depth download depth.
     * @return download result.
     */
    @Override
    public Result download(String url, int depth) {
        final Phaser phaser = new Phaser(1);
        final Set<String> downloaded = new ConcurrentSkipListSet<>();
        final Map<String, IOException> errors = new ConcurrentHashMap<>();
        CurrentTasks currentTasks = new CurrentTasks(new HashSet<>(List.of(url)));

        for (int i = 1; i <= depth; i++) {
            currentTasks = downloadOnDepth(currentTasks, downloaded, errors, phaser);
        }

        return new Result(new ArrayList<>(downloaded), errors);
    }

    private CurrentTasks downloadOnDepth(final CurrentTasks currentTasks, final Set<String> downloaded,
                                         final Map<String, IOException> errors, final Phaser phaser) {
        final Set<String> urls = new ConcurrentSkipListSet<>();

        currentTasks.getUrls().forEach(currentUrl -> {
            if (!downloaded.contains(currentUrl) && !errors.containsKey(currentUrl)) {
                phaser.register();

                final CurrentDocument currentDocument = new CurrentDocument(null);

                downloaders.submit(() -> {
                    try {
                        currentDocument.setDocument(downloader.download(currentUrl));
                        downloaded.add(currentUrl);

                        phaser.register();
                        extractors.submit(() -> {
                            try {
                                if (currentDocument.getDocument() != null) {
                                    urls.addAll(currentDocument.getDocument().extractLinks());
                                }
                            } catch (IOException ignored) {
                            }

                            phaser.arriveAndDeregister();
                        });
                    } catch (IOException e) {
                        errors.put(currentUrl, e);
                    }

                    phaser.arriveAndDeregister();
                });
            }
        });

        phaser.arriveAndAwaitAdvance();
        return new CurrentTasks(urls);
    }

    static class CurrentDocument {
        private Document document;

        CurrentDocument(final Document document) {
            this.document = document;
        }

        public void setDocument(Document document) {
            this.document = document;
        }

        public Document getDocument() {
            return document;
        }
    }

    static class CurrentTasks {
        private final Queue<String> urls;

        public CurrentTasks(final Set<String> urls) {
            this.urls = new ArrayDeque<>(urls);
        }

        public Queue<String> getUrls() {
            return urls;
        }
    }

    /**
     * Closes this web-crawler, relinquishing any allocated resources.
     */
    @Override
    public void close() {
        closeService(downloaders);
        closeService(extractors);
    }

    private void closeService(final ExecutorService executorService) {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(TIMEOUT, TIME_UNIT)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }

    /**
     * Create {@link WebCrawler} and invoke {@link #download} on args.
     * Usage: java WebCrawler <url> <depth> <downloads> <extractors> <perHost>
     *
     * @param args user input
     */
    public static void main(String[] args) {
        if (args == null || args.length != 5 || Arrays.stream(args).anyMatch(Objects::isNull)) {
            System.err.println("Usage: java WebCrawler <url> <depth> <downloads> <extractors> <perHost>");
            return;
        }

        try (final WebCrawler webCrawler = new WebCrawler(new CachingDownloader(TIME_SCALE),
                Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]))) {

            final Result result = webCrawler.download(args[0], Integer.parseInt(args[1]));
            System.out.println("Downloaded: " + result.getDownloaded().toString());
            System.err.println("Errors: " + result.getErrors().toString());
        } catch (IOException e) {
            System.err.println("Downloader cannot be created. Message: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("<depth> <downloads> <extractors> <perHost> must be integers. Message: " + e.getMessage());
        }
    }
}
