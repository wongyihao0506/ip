/**
 * The GrowlerException class is a custom exception used in the Growler application.
 * It extends the {@link Exception} class and allows for handling specific errors that occur in the application.
 */
public class GrowlerException extends Exception {

    /**
     * Constructs a new GrowlerException with the specified detail message.
     *
     * @param message The detail message to describe the exception.
     */
    public GrowlerException(String message) {
        super(message);
    }
}
