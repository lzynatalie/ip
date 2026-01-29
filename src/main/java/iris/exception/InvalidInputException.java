package iris.exception;

/**
 * Thrown to indicate that a user input was invalid.
 */
public class InvalidInputException extends IrisException {
    public InvalidInputException(String message) {
        super("Invalid input: " + message);
    }
}
