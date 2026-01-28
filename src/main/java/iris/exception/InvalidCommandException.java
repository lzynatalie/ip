package iris.exception;

public class InvalidCommandException extends IrisException {
    public InvalidCommandException() {
        super("Sorry, I don't know what that means :-(");
    }

    public InvalidCommandException(String message) {
        super(message);
    }
}
