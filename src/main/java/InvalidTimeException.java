/**
 * A DukeException for when the user inputs the time of a Deadline or Event task in an improper format.
 */
class InvalidTimeException extends DukeException {

    InvalidTimeException(String msg) {
        super(msg);
    }

}
