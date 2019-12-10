/**
 * This is a class of exception where the date supplied is not in the dd/MM/yyyy HHmm format.
 * @author Choong Yong Xin
 */
public class WrongDateFormatDukeException extends DukeException {
    public WrongDateFormatDukeException() {
        super("OOPS!!! The date is of the wrong format.");
    }
}

