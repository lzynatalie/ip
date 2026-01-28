package iris.exception;

public class InvalidInputException extends IrisException {
    public InvalidInputException(String message) {
        super("Invalid input: " + message);
    }
}
