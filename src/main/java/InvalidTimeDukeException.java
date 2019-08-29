public class InvalidTimeDukeException extends DukeException {
    public InvalidTimeDukeException() {
        super("invalid date");
    }

    @Override
    public String toString() {
        return "Sorry, Dates should be in format 'dd/mm/yyyy hh:mm'";
    }
}
