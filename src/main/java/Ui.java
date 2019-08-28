import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {

    private final Scanner in;

    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Read a command from the user.
     */
    public String readCommand() {
        String fullInputLine = in.nextLine();
        return fullInputLine;
    }

    /**
     * Add a border to all inputs.
     * @param input String input
     */
    public void addBorder (String input){

        String border = "____________________________________________________________";

        out.println(border + "\n\n" + input + "\n" + border + "\n");
    }

    /**
     * Greet the user when they start up Duke.
     */
    public void welcomeMessage() {
        addBorder("Hello! I'm Duke\n" + "What can I do for you?");
    }

    /**
     * Display exit message to user when they leave Duke.
     */
    public void exitMessage () {
        addBorder("Bye. Hope to see you again soon!");
    }

    /**
     * Show user the list of tasks they have.
     * @param tasks ArrayList of tasks.
     */
    public void printList (TaskList tasks) {
        String str = "Here are the tasks in your list:\n";

        for (int i = 1; i < tasks.getSize() + 1; i++) {
            if (i == tasks.getSize()) {
                str += i + "." + tasks.getTask(i - 1);
            } else {
                str += i + "." + tasks.getTask(i - 1) + "\n";
            }
        }

        addBorder(str);
    }

    /**
     * Relay errors during execution of Duke to user.
     * @param input String input.
     */
    public void showErrors(String input) {
        addBorder(input);
    }
}
