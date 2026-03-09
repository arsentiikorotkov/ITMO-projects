package generator;

public class GeneratorException extends RuntimeException {

    public GeneratorException(final String message) {
        super("Generation error: " + message);
    }
}
