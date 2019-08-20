public class DukeDeadlineException extends DukeException {

    public DukeDeadlineException() {
        super();
    }

    public DukeDeadlineException(String message) {
        super(message);
        CmdInterface.printHBars(message);
    }

}
