public class DukeNoCorrespondingTaskException extends DukeException {
    public DukeNoCorrespondingTaskException(int number) {
        super("The number " + number + " does not correspond to a task in the list.");
    }

    public DukeNoCorrespondingTaskException(String s) {
        super(s);
    }
}
