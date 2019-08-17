public class DukeException extends Exception {
    String message;
    public DukeException(String message){
        super(message);
        this.message = message;
    }

    public String toString() {
        return "\t☹ OOPS!!! " + this.message;
    }
}
