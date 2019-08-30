import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.PatternSyntaxException;

public class DukeBot {
    private Scanner in;
    private TaskList taskList = new TaskList();
    private String welcomeMessage = "What can I do for you?";

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
        String inputString; // stores input from user

        this.taskList = new TaskList();

        // continuously take in user input until terminated
        while (in.hasNextLine()) {
            inputString = in.next();
            if (inputString.equals("bye")) {
                // if "bye", terminate
                // this would terminate if the user enters a line beginning with "bye"
                // todo: check if this is expected behaviour?
                break;
            }
            process(inputString);
        }

        // say goodbye upon exit
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Creates the given input string.
     *
     * @param input input string given by user.
     */
    private void process(String input) {
        // todo: extract list and done into TaskList class
        if (input.equals("list")) {
            // if "list", print list
            this.taskList.printList();
        } else if (input.equals("done")) {
            try {
                int taskId = in.nextInt(); // extract the task ID entered by user
                this.taskList.getTask(taskId).markAsDone(); // mark task as done
            } catch (InputMismatchException e) {
                // user input after "done" is not an int

            } catch (NoSuchElementException e) {
                // user input after "done" is blank

            }
        } else if (input.equals("todo")) {
            // get the rest of the line
            String remainingInput;
            String taskDescription;
            String taskDeadline;
            try {
                remainingInput = in.nextLine();
            } catch (NoSuchElementException e) {
                // user input after type of task is blank
            } finally {
                try {
                    // separate the remaining input
                    String[] remainingInputArray = remainingInput.split("/by");
                } catch (PatternSyntaxException e) {

                }
                // what's with these nested try blocks??? fix it pls :(
                // how to implement checks for different task types without repeating code?
            }

        } else {
            // otherwise, add to list
            this.taskList.addToList(input);
        }
    }
}
