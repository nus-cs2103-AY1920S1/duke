/**
 * Represents a response from Duke due to some user input.
 * A response provides the getter methods to its message and error status
 */
class Response {

    private String message;
    private boolean isError;

    /**
     * Initialises a Response that has a default isDone field of false.
     *
     * @param message Message to be displayed
     * @param isError Error status tagged to the message
     */
    Response(String message, boolean isError) {
        this.message = message;
        this.isError = isError;
    }

    /**
     * Returns the message to be shown to the user
     *
     * @return Message to be displayed
     */
    String getMessage() {
        return message;
    }

    /**
     * Returns the error status tagged to the message
     *
     * @return Error status
     */
    boolean getErrorStatus() {
        return isError;
    }




}
