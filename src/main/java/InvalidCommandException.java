public class InvalidCommandException extends DukeException {

    public InvalidCommandException(String message){
        super(String.format("%s command doesn't exist!", message));
    }
}
