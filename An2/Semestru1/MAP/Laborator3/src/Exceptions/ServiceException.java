package Exceptions;

public class ServiceException extends RuntimeException {
    /**
     * Creates a service exception with the given message
     * @param message a description of the error
     */
    public ServiceException(String message) {
        super(message);
    }
}
