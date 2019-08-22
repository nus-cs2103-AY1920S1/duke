public class DukeException extends Exception{

    protected String exceptionType;

    public DukeException(String exceptionType) {
        this.exceptionType = exceptionType;
    }

    public String toString() {
        if (exceptionType.equals("done")) {
            return ":( OOPS!!! The index of a done cannot be empty.";
        } else if (exceptionType.equals("delete")) {
            return ":( OOPS!!! The index of a delete cannot be empty.";
        } else if (exceptionType.equals("todo")) {
            return ":( OOPS!!! The description of a todo cannot be empty.";
        } else if (exceptionType.equals("index")) {
            return ":( OOPS!!! No such task in the list.";
        } else if (exceptionType.equals("deadline")) {
            return ":( OPPS!!! Invalid input format for deadline.";
        } else if (exceptionType.equals("event")) {
            return ":( OPPS!!! Invalid input format for event";
        } else {
            return ":( OOPS!!! I'm sorry, but I don't know what that means :-(";
        }
    }
}