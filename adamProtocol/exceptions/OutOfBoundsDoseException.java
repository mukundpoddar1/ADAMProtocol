package adamProtocol.exceptions;

public class OutOfBoundsDoseException extends Exception {
    String message;
    public OutOfBoundsDoseException(String message) {
        this.message=message;
    }
}
