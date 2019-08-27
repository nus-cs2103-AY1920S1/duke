package duke;

public class DukeException extends Exception{

    /**
     * Creates a Duke Exception object
     *
     * @param message Error message to print.
     */
    public DukeException(String message){
        System.out.println("    ____________________________________________________________");
        System.out.println("     \u2639 OOPS!!! " + message);
        System.out.println("    ____________________________________________________________");
    }
}
