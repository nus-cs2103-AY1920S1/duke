public class DukeException extends Exception {
    private String message;

    public DukeException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return "OOPS!!!" + this.message;
    }
}
