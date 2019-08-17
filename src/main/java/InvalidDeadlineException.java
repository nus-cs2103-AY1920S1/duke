public class InvalidDeadlineException extends Exception {

    public String message;

    public InvalidDeadlineException(String message) {
        this.message = message;
    }

    public String toString() {
        return "☹ OOPS!!! " + message;
    }

}
