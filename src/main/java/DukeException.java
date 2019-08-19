public class DukeException extends Exception {

    public DukeException(){
        super("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public DukeException(String message){
        super(message);
    }

    public DukeException(String attrib, String task){
        super("\u2639 OOPS!!! The " + attrib + " of a" +
                (task.startsWith("a|e|i|o|u") ? "n " + task : ' ' + task) + " cannot be empty.");
    }
}
