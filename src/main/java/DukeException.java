public class DukeException extends Exception {
    protected String oops = "☹ OOPS!!! ";
    @Override
    public String toString() {
        return oops + "I'm sorry, but I don't know what that means :-(";
    }
}
