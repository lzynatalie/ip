package iris.exception;

/**
 * Thrown to indicate that a command cannot be used in that instance.
 */
public class InvalidCommandException extends IrisException {
    public InvalidCommandException() {
        super("Sorry, I don't know what that means :-(");
    }

    public InvalidCommandException(String message) {
        super(message);
    }
}
