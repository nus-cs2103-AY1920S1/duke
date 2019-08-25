public class DukeException extends Exception {

    protected DukeExceptionType type;

    public DukeException(String message, DukeExceptionType type){
        super(message);
        this.type = type;
    }

}