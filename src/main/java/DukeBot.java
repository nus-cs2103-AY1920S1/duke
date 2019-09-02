import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class DukeBot {
    private Scanner in;
    private TaskList taskList = new TaskList();
    private static final String welcomeMessage = "What can I do for you?";

    /**
     * Starts DukeBot and prints welcome message.
     */

    public void initialise() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(welcomeMessage);
        in = new Scanner(System.in);
        run();
    }

    /**
     * Reads input from the user and passes it to process().
     */
    private void run() {
        String input; // stores input from user

        this.taskList = new TaskList();

        // continuously take in user input until terminated
        while (in.hasNextLine()) {
            input = in.nextLine();
            if (input.equals("bye")) {
                // if "bye", terminate
                // todo: is there a way to combine this into processInput()?
                break;
            }

            try {
                this.taskList.processInput(input);
            } catch (InputMismatchException e) {
                System.out.println("Sorry! I don't know what that means :(");
            }
        }

        // say goodbye upon exit
        System.out.println("Bye. Hope to see you again soon!");
    }
}
