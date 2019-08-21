public class DeleteException extends Exception {

    public DeleteException() {
        super("OOPS!!! The description of a delete cannot be empty.");
    }

    public DeleteException(String msg) {
        super(msg);
    }

}