public class DukeInvalidTaskException extends DukeException {
    String task;
    public DukeInvalidTaskException (String task) {
        this.task = task;
    }

    public DukeInvalidTaskException (int task) {
        this.task = Integer.toString(task);
    }

    @Override
    public String toString() {
        return oops + "Task " + task + " does not exist.";
    }
}
