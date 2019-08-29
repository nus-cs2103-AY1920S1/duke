package duke;
import java.util.Scanner;

/**
 * This class is responsible for all input and output the user interface.
 */
public class Ui {
    private Scanner input;
    private Parser parser;

    /**
     * Returns an initialized UI instance.
     */
    public Ui() {
        this.input = new Scanner(System.in);
    }

    /**
     * Prints the error message of a DukeException.
     * @param e DukeException instance with the message to be printed
     */
    public void showError(DukeException e) {
        printOutput(e.getMessage());
    }

    /**
     * Prints a generic String output.
     * @param output String output to be printed
     */
    public void printOutput(String output) {
        String line = "    _____________________________"
                + "_______________________________\n";

        // Indent and process output line
        output = "      " + output.replaceAll("\n", "\n      ") + '\n';

        System.out.println(line + output + line);
    }

    /**
     * Prints duke's greeting message.
     */
    public void greetHello() {
        printOutput("Hello I'm Duke\nWhat can I do for you?");
    }

    /**
     * Prints duke's goodbye greeting message.
     */
    public void greetBye() {
        printOutput("Bye. Hope to see you again soon!");
    }

    /**
     * Runs input loop for the user to modify the TaskList for duke.
     * @param taskList TaskList for the user's commands to act on.
     * @return a modified TaskList after the user has exited the loop.
     */
    public TaskList runInputLoop(TaskList taskList) {
        this.parser = new Parser(taskList);
        String input;

        // Run input loop
        while (!(input = this.input.nextLine()).equals("bye")) {

            try {
                String output = parser.parse(input);
                printOutput(output);
            } catch (DukeException e) {
                printOutput(e.getMessage());
            }
        }

        return taskList;
    }
}
