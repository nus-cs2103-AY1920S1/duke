public class UnableToReadFileException extends DukeException {

    public UnableToReadFileException(int index){
        super("Unable to read line " + index);
    }
}
