package Duke.exception;

public class DukeException extends Exception {
    String errorMsg;

    public DukeException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        String line = "____________________________________________________________";
        return String.format("\t%s\n\t ☹ OOPS!!! %s\n\t%s",line, this.errorMsg, line);
    }
}
