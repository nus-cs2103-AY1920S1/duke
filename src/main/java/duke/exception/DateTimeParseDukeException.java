package duke.exception;

public class DateTimeParseDukeException extends DukeException {
    /**
     * Used to notify DateTime cannot be parsed and hint user with correct usage.
     */
    public DateTimeParseDukeException() {
        super("DateTime cannot be resolved.\nExpected formats:\n2/12/2019 1800\nT1800\n2 Dec "
            + "2019\ndecember2T1300\nAnd other non-ambiguous derivatives.\nLeftmost is taken as day unless it is "
            + "non-numerical month.\nNon-provided datetime defaults to current system relative.");
    }
}
