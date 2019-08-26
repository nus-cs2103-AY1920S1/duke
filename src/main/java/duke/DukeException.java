package duke;

public class DukeException extends Exception{
    public DukeException(String message){
        System.out.println("    ____________________________________________________________");
        System.out.println("     ☹ OOPS!!! " + message);
        System.out.println("    ____________________________________________________________");
    }
}
