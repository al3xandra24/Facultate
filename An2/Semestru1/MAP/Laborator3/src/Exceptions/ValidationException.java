package Exceptions;

public class ValidationException extends RuntimeException {
    /**
     * Creates a validation exception with the given message
     * @param message a description of the error
     */
    public ValidationException(String message) {
        super(message);
    }
}
