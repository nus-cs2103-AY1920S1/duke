import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

/**
 * Represents a UI which interacts with the user.
 * @author Ang Kai Qi
 * @version 0.1.3
 */
public class UI {
    protected PrintStream ps;

    /**
     * Creates a UI object to print out interactions to the user.
     * Catches for unsupported encodings.
     */
    public UI() {
        try {
            this.ps = new PrintStream(System.out, true, "UTF-8");
        } catch (UnsupportedEncodingException uee) {
            System.out.println("UI: Something serious happened here...");
        }
    }

    /**
     * Prints out whatever is given to it.
     * Works especially well for UTF-8 encoded characters.
     * @param s String that is passed to UI.
     */
    public void echo(String s) {
        ps.println(s);
    }

    /**
     * Generic Greeting at the start of the programme.
     */
    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    /**
     * Generic farewell at the end of the programme.
     */
    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
