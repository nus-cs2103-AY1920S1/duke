public class DukeNoCorrespondingTaskException extends DukeException {
    public DukeNoCorrespondingTaskException(int number) {
        super("The following number does not correspond to a task in the list: ", Integer.toString(number));
    }
}
