public class InvalidDateInputException extends Exception {

    private String message;

    public InvalidDateInputException(String message) {
        this.message = message;
    }

    public String toString() {
        return "☹ OOPS!!! " + this.message;
    }
}
