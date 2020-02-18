package duke.exception;

public class InvalidDateTimeException extends DukeException {

    public String toString() {
        return "OOPS!!! Please provide the date and time in the correct format as follows: "
                + "DD/MM/YYYY HHMM (eg. 2/12/2019 1800)";
    }
}
