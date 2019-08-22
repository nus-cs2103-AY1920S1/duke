public class DoneException extends Exception{
    public DoneException() {};

    @Override
    public String toString() {
        return " ☹ OOPS!!! You must specify a Task to be done!";
    }
}
