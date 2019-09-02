public class MissingDeadlineException extends Exception{

    public String toString() {
        return "☹ OOPS!!! The description of a deadline cannot be empty.";
    }

}
