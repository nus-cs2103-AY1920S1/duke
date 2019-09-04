/** 
* Custom DukeException class to throw custom error messages
*/ 

public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
