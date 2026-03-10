package info.kgeorgiy.ja.korotkov.implementor;

import info.kgeorgiy.java.advanced.implementor.ImplerException;
import info.kgeorgiy.java.advanced.implementor.JarImpler;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.jar.Attributes;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;


/**
 * Implementation of {@link JarImpler} interface.
 *
 * @author Arsentiy Korotkov
 */
public class Implementor implements JarImpler {
    /**
     * Suffix for generating implementation.
     */
    private static final String SUFFIX = "Impl";
    /**
     * Extension for source code.
     */
    private static final String EXTENSION_JAVA = ".java";
    /**
     * Extension for compiled code.
     */
    private static final String EXTENSION_CLASS = ".class";

    /**
     * Produces code implementing class or interface specified by provided {@code token}.
     * <p>
     * Generated class' name should be the same as the class name of the type token with {@code Impl} suffix
     * added. Generated source code should be placed in the correct subdirectory of the specified
     * {@code root} directory and have correct file name. For example, the implementation of the
     * interface {@link java.util.List} should go to {@code $root/java/util/ListImpl.java}
     *
     * @param token type token to create implementation for.
     * @param root  root directory.
     * @throws info.kgeorgiy.java.advanced.implementor.ImplerException when implementation cannot be
     *                                                                 generated.
     */
    @Override
    public void implement(final Class<?> token, final Path root) throws ImplerException {
        validateToken(token);

        final Path path = fullPath(token, root, EXTENSION_JAVA);

        createDirs(path);

        genImpl(token, path);
    }

    /**
     * Creates all directories in the path to the file.
     *
     * @param path a path to the file
     * @throws ImplerException if an I/O error occurs
     */
    private void createDirs(final Path path) throws ImplerException {
        try {
            Files.createDirectories(path.getParent());
        } catch (IOException e) {
            throw new ImplerException("Problems with creating directories. Message: " + e.getMessage());
        }
    }

    /**
     * Creates implementation for token in path.
     *
     * @param token a type token to create implementation with {@link #implement}
     * @param path current path to create implementation
     * @throws ImplerException if io problems with creating class
     */
    private void genImpl(final Class<?> token, final Path path) throws ImplerException {
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            if (!token.getPackageName().equals("")) {
                newLine(writer, String.format("package %s;", token.getPackageName()));
                newLine(writer, "");
            }

            newLine(writer, String.format("public class %sImpl implements %s {",
                    token.getSimpleName(), token.getCanonicalName()));

            methods(writer, token);

            newLine(writer, "}");
        } catch (IOException e) {
            throw new ImplerException("Problems with creating class. Message: " + e.getMessage());
        }
    }

    /**
     * Writes methods of token by current writer.
     *
     * @param writer current writer
     * @param token a type token to create implementation with {@link #implement}
     * @throws IOException If an I/O error occurs
     */
    private void methods(final BufferedWriter writer, final Class<?> token) throws IOException {
        for (final Method method : filter(token)) {
            newLine(writer, "    @Override");
            newLine(writer, signature(method));
            newLine(writer, implementation(method));
            newLine(writer, "    }");
        }
    }

    /**
     * Creates signature of method.
     *
     * @param method a method whose signature is expected
     * @return a string with the {@code method} signature
     */
    private String signature(final Method method) {
        return String.format("    public %s %s(%s) %s {",
                method.getReturnType().getCanonicalName(),
                method.getName(),
                params(method),
                exceptions(method));
    }

    /**
     * Creates listing the exceptions that the method can throw.
     *
     * @param method a method whose list of exceptions is expected
     * @return a string with the {@code method} exceptions
     */
    private String exceptions(final Method method) {
        final String e = Arrays.stream(method.getExceptionTypes()).map(Class::getCanonicalName)
                .collect(Collectors.joining(", "));
        return method.getExceptionTypes().length > 0 ? String.format("throws %s", e) : "";
    }

    /**
     * Creates listing the params of method.
     *
     * @param method a method whose list of params is expected
     * @return a string with the {@code method} params
     */
    private String params(final Method method) {
        return Arrays.stream(method.getParameters())
                .map(parameter ->
                        String.format("%s %s",
                                parameter.getType().getCanonicalName(),
                                parameter.getName()))
                .collect(Collectors.joining(", "));
    }

    /**
     * Creates implementation of method.
     *
     * @param method a method whose implementation is expected
     * @return a string with the {@code method} implementation
     */
    private String implementation(final Method method) {
        StringBuilder res = new StringBuilder("        return ");
        if (method.getReturnType() == void.class) {
            res.append(";");
        } else if (method.getReturnType() == boolean.class) {
            res.append("true;");
        } else if (method.getReturnType().isPrimitive()) {
            res.append("0;");
        } else {
            res.append("null;");
        }
        return res.toString();
    }

    /**
     * Creates list of NonStatic token methods.
     *
     * @param token a type token to filter methods
     * @return a list of NonStatic {@code token} methods
     */
    private List<Method> filter(final Class<?> token) {
        return Arrays.stream(token.getMethods()).filter(method -> !Modifier.isStatic(method.getModifiers())).collect(Collectors.toList());
    }

    /**
     * Writes string and enter to the next line.
     *
     * @param writer a current writer
     * @param str    a line to write
     * @throws IOException If an I/O error occurs
     */
    private void newLine(final BufferedWriter writer, String str) throws IOException {
        writer.write(escape(str));
        writer.newLine();
    }

    /**
     * Creates expected package name of token with {@link File#separatorChar}.
     *
     * @param token a type token whose package name is expected
     * @return package name of {@code token} with current file separator
     */
    private String packageName(final Class<?> token) {
        return token.getPackageName().replace('.', File.separatorChar);
    }

    /**
     * Creates expected class name of token with suffix and extension.
     *
     * @param token     a type token whose class name is expected
     * @param extension expected extension
     * @return {@link Class#getSimpleName()} of token with {@value SUFFIX} and {@code extension}
     */
    private String className(final Class<?> token, final String extension) {
        return String.format("%s%s%s", token.getSimpleName(), SUFFIX, extension);
    }

    /**
     * Checks validity of token.
     *
     * @param token a type token to create implementation with {@link #implement}
     * @throws ImplerException if this {@code  token} is a private interface or not an interface
     */
    private void validateToken(final Class<?> token) throws ImplerException {
        if (!Modifier.isInterface(token.getModifiers())) {
            throw new ImplerException("It is not interface.");
        }
        if (Modifier.isPrivate(token.getModifiers())) {
            throw new ImplerException("This interface is private.");
        }
    }

    /**
     * Produces <var>.jar</var> file implementing class or interface specified by provided <var>token</var>.
     * <p>
     * Generated class' name should be the same as the class name of the type token with <var>Impl</var> suffix
     * added.
     *
     * @param token   type token to create implementation for
     * @param jarFile target <var>.jar</var> file
     * @throws ImplerException when implementation cannot be generated
     *                         or could not find java compiler
     */
    @Override
    public void implementJar(Class<?> token, Path jarFile) throws ImplerException {
        final Path jarDir = jarFile.getParent();
        if (jarDir == null) {
            throw new ImplerException("not exist parent directory");
        }

        implement(token, jarDir);

        compile(token, jarDir);

        genJar(token, jarDir, jarFile);

        clear(token, jarDir);
    }

    /**
     * Creates jar for token in jarDir.
     *
     * @param token  type token to create jar with {@link #implementJar}
     * @param jarDir expected dir to jar which gens by {@link #implementJar}
     * @param jarFile target <var>.jar</var> file
     * @throws ImplerException if io problems with creating jar
     */
    private void genJar(final Class<?> token, final Path jarDir, final Path jarFile) throws ImplerException {
        Manifest manifest = new Manifest();
        manifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION, "1.0");

        try (JarOutputStream os = new JarOutputStream(Files.newOutputStream(jarFile), manifest)) {
            os.putNextEntry(new ZipEntry(String.format(
                    "%s/%sImpl.class",
                    token.getPackageName().replace('.', '/'),
                    token.getSimpleName()
            )));
            Files.copy(fullPath(token, jarDir, EXTENSION_CLASS), os);
        } catch (IOException e) {
            throw new ImplerException("Problems with creating jar. Message: " + e.getMessage());
        }
    }

    /**
     * Compiles generating implementation from token by {@link #implement} in dir.
     *
     * @param token a type token to create jar with {@link #implementJar}
     * @param jarDir expected dir to jar which gens by {@link #implementJar}
     * @throws ImplerException if it could not find java compiler
     */
    private void compile(final Class<?> token, final Path jarDir) throws ImplerException {
        final JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler == null) {
            throw new ImplerException("Could not find java compiler");
        }
        final String[] args = new String[]{"-cp", getClassPath(token), "-encoding", "UTF-8",
                fullPath(token, jarDir, EXTENSION_JAVA).toString()};
        compiler.run(null, null, null, args);
    }

    /**
     * Clean the directory from the file after {@link #implementJar}
     *
     * @param token a type token to create jar with {@link #implementJar}
     * @param jarDir expected dir to jar which gens by {@link #implementJar}
     */
    private void clear(final Class<?> token, final Path jarDir) {
        try {
            Files.deleteIfExists(fullPath(token, jarDir, EXTENSION_CLASS));
        } catch (IOException e) {
            System.err.println("IO problems with deleting class file. Message: " + e.getMessage());
        } catch (SecurityException e) {
            System.err.println("No rights to delete. Message: " + e.getMessage());
        }
    }

    /**
     * Creates path to implementation of token in root with expected extension.
     *
     * @param token     a type token to create implementation with {@link #implement}
     * @param root      a directory of implementation
     * @param extension an extension of implementation
     * @return a path, which was resolved by {@link Path#resolve(String)} from root to implementation directory
     */
    private Path fullPath(final Class<?> token, final Path root, final String extension) {
        return root.resolve(packageName(token)).resolve(className(token, extension));
    }

    /**
     * Creates classpath of type token.
     *
     * @param token type token to create implementation with {@link #implement}
     * @return string of expected classpath of {@code token}
     * @throws ImplerException if URL cannot be converted to a URI
     */
    private String getClassPath(final Class<?> token) throws ImplerException {
        try {
            return Path.of(token.getProtectionDomain().getCodeSource().getLocation().toURI()).toString();
        } catch (final URISyntaxException e) {
            throw new ImplerException("URL cannot be converted to a URI. Message: " + e.getMessage());
        }
    }

    /**
     * Escapes all non-ASCII chars and works correctly for low Unicode code points.
     *
     * @param str a line for conversion
     * @return converted {@code str}
     */
    private String escape(final String str) {
        StringBuilder builder = new StringBuilder();

        for (char ch : str.toCharArray()) {
            if (ch >= 128)
                builder.append("\\u").append(String.format("%04X", (int) ch));
            else
                builder.append(ch);
        }

        return builder.toString();
    }

    /**
     * Creates implementation or jar.
     * Start {@link #implement} or {@link #implementJar} depending on the arguments.
     * java Implementor [-jar] <canonical_name> <root>
     * If you need to start {@link #implement}, args must be: canonical_name, root.
     * If you need to start {@link #implementJar}, args must be: -jar, canonical_name, root.
     *
     * @param args user input
     */
    public static void main(String[] args) {
        if (args == null || (args.length != 2 && args.length != 3) ||
                Arrays.stream(args).anyMatch(Objects::isNull) ||
                (args.length == 3 && !args[0].equals("-jar"))) {
            System.err.println("Usage: java Implementor [-jar] <canonical_name> <root>");
            return;
        }

        final Implementor implementor = new Implementor();
        try {
            if (args.length == 2) {
                implementor.implement(Class.forName(args[0]), Path.of(args[1]));
            }
            if (args.length == 3) {
                implementor.implementJar(Class.forName(args[1]), Path.of(args[2]));
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found. Message: " + e.getMessage());
        } catch (InvalidPathException e) {
            System.err.println("Invalid path. Message: " + e.getMessage());
        } catch (ImplerException e) {
            System.err.println("Implementation cannot be generated. Message: " + e.getMessage());
        }
    }
}