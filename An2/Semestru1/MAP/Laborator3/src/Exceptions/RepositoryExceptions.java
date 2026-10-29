package Exceptions;

public class RepositoryExceptions extends RuntimeException {
    /**
     * Creates a repository exception with the given message
     * @param message a description of the error
     */
    public RepositoryExceptions(String message) {
        super(message);
    }

    /**
     * Creates a repository exception that wraps the original exception
     * @param message a description of the error
     * @param cause the original exception
     */
    public RepositoryExceptions(String message, Throwable cause) {
        super(message, cause);
    }
}
