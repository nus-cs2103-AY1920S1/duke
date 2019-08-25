public class DukeInvalidTaskFormatException extends DukeException {
    String task;

    DukeInvalidTaskFormatException (String task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return oops + "\"" + task + "\" is an invalid task format.";
    }
}
