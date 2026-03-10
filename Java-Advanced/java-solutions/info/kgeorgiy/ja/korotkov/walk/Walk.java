package info.kgeorgiy.ja.korotkov.walk;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Walk {
    public static final int BUFFER_SIZE = 1 << 16;
    public static final String FALLING_CALCULATEHASH = "0".repeat(64);
    public static final String HASH_ALGORITHM = "SHA-256";

    public static void main(String[] args) {
        if (args == null || args.length != 2 || args[0] == null || args[1] == null) {
            System.err.println("Usage: java Walk <input_file> <output_file>");
            return;
        }

        new Walk().run(args);
    }

    public void run(String[] args) {
        String inputFilePath = args[0];
        String outputFilePath = args[1];

        Path inputFile = initialize(inputFilePath, false);
        Path outputFile = initialize(outputFilePath, true);
        if (outputFile == null || inputFile == null) {
            return;
        }

        try (BufferedReader reader = Files.newBufferedReader(inputFile, StandardCharsets.UTF_8)) {
            try (BufferedWriter writer = Files.newBufferedWriter(outputFile, StandardCharsets.UTF_8)) {
                MessageDigest md;
                try {
                    md = MessageDigest.getInstance(HASH_ALGORITHM);
                } catch (NoSuchAlgorithmException e) {
                    System.err.println("No such hashing algorithm: " + e.getMessage());
                    return;
                }
                String currentFileName;
                while ((currentFileName = reader.readLine()) != null) {
                    String currentFileHash = calculateHash(currentFileName, md) + " " + currentFileName;
                    try {
                        writer.write(currentFileHash);
                        writer.newLine();
                        writer.flush();
                    } catch (IOException e) {
                        System.err.println("Problems with writing hashes to file. Message" + e.getMessage());
                    }
                }
            } catch (IOException e) {
                System.err.println("Open problems with output file or readline errors. Message: " + e.getMessage());
            }
        } catch (IOException e) {
            System.err.println("Open problems with input file. Message: " + e.getMessage());
        }
    }

    public String calculateHash(String file, MessageDigest md) {
        try (InputStream in = Files.newInputStream(Paths.get(file))) {
            md.reset();
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                md.update(buffer, 0, bytesRead);
            }
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return new String(sb);
        } catch (IOException | InvalidPathException e) {
            return FALLING_CALCULATEHASH;
        }
    }

    private Path initialize(String pathToFile, boolean isOutput) {
        Path path;
        try {
            path = Paths.get(pathToFile);
        } catch (InvalidPathException e) {
            System.err.println("Problems with path to inputfile or outputfile. Message: " + e.getMessage());
            return null;
        }

        if (isOutput) {
            try {
                Path parent = path.getParent();
                if (parent != null) {
                    Files.createDirectories(parent);
                }
            } catch (IOException e) {
                System.err.println("Some io problems. Message: " + e.getMessage());
                return null;
            }
        } else if (!Files.exists(path)) {
            path = null;
        }

        return path;
    }
}
