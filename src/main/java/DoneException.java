public class DoneException extends Exception {

    public DoneException() {
        super("OOPS!!! The description of a done cannot be empty, please indicate which task is done :)");
    }

    public DoneException(String msg) {
        super(msg);
    }

}