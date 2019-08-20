public class EmptyDescException extends DukeException {
    String type;

    public EmptyDescException(String type) {
        this.type = type;
    }

    public String toString(){
        return " OOPS!!! The description of a " + type + " cannot be empty.";
    }
}
