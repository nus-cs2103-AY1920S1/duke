package slave.exception;

public class MissingDateException extends DukeException {
    public MissingDateException(){
        super("Date is missing!");
    }
}
