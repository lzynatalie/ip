package iris.exception;

/**
 * Thrown to indicate that an error has occurred in the program.
 */
public class IrisException extends Exception {
    public IrisException(String message) {
        super(message);
    }
}
