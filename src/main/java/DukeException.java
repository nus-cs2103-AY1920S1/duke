public class DukeException extends Exception {
    DukeException(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "DukeException: " + this.getMessage();
    }
}
