public class InvalidDateInputException extends DukeException {

    private String message;

    public InvalidDateInputException(String message) {
        this.message = message;
    }

    public String toString() {
        return "OOPS!!! " + this.message;
    }
}
