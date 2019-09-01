public class DukeException extends Exception {
    private String errorMessage;

    protected DukeException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "____________________________________________________________\n"
                + this.errorMessage
                + "\n____________________________________________________________";
    }
}
