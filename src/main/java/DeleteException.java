public class DeleteException extends Exception{
    public DeleteException() {};

    @Override
    public String toString() {
        return " ☹ OOPS!!! There was an error with deleting!";
    }
}
