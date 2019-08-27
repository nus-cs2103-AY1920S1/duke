package slave.exception;

public class CannotBeFoundException extends DukeException {

    public CannotBeFoundException(String term){
        super(String.format("%s cannot be found!", term));
    }
}
