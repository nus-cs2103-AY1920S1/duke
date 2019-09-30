package duke.exception;

public class InvalidEventException extends DukeException {
    public String toString() {
        return "OOPS!!! Please provide the starting date and time and the ending time in the correct format "
                + "as follows: DD/MM/YYYY HHMM /to HHMM (eg. 2/12/2019 1800 /to 2100)";
    }
}
