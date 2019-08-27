package slave.exception;

public class NoStorageFileDetectedException extends DukeException {

    public NoStorageFileDetectedException(){
        super("No storage file detected!");
    }
}
