public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        String result = "\t____________________________________________________________";
        result = result
                + "\n\t ☹ OOPS!!! "
                + getMessage()
                + "\n\t____________________________________________________________";
        return result;
    }
}
