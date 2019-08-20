public class DukeEventException extends DukeException {

    public DukeEventException () {
        super();
    }

    public DukeEventException (String message) {
        super(message);
        CmdInterface.printHBars(message);
    }

}
