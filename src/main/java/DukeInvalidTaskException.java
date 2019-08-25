public class DukeInvalidTaskException extends DukeException {
    String task;

    DukeInvalidTaskException (String task) {
        this.task = task;
    }

    DukeInvalidTaskException (int task) {
        this.task = Integer.toString(task);
    }

    @Override
    public String toString() {
        return oops + "Task " + task + " does not exist.";
    }
}
