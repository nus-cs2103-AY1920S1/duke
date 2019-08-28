package duke.exception;

/**
 * An exception when an unknown command is given to Duke.
 */
public class UnknownCmdDukeException  extends DukeException{
    public UnknownCmdDukeException(String s) {
        super(s);
    }
}
