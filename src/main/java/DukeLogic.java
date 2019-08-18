import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DukeLogic {

    /*===============================
    ||    Private Static Strings   ||
    =================================*/

    private final String DUKE_ASCII_LOGO = " ____        _        \n"
                                        + "|  _ \\ _   _| | _____ \n"
                                        + "| | | | | | | |/ / _ \\\n"
                                        + "| |_| | |_| |   <  __/\n"
                                        + "|____/ \\__,_|_|\\_\\___|\n";
    private final String DUKE_WELCOME_MESSAGE = "Hello! I'm Duke\n\t What can I do for you?";
    private final String SEPARATOR = "____________________________________________________________";
    private final String DUKE_EXIT_COMMAND = "bye";
    private final String DUKE_LIST_COMMAND = "list";
    private final String DUKE_DONE_COMMAND = "done";
    private final String DUKE_EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    private final int DUKE_MAXIMUM_TASKS = 100;

    /*===============================
    ||    Private Class Variables  ||
    =================================*/

    private StringBuilder sb;
    private Scanner scanner;
    private List<DukeTask> userDukeTasks;

    /*===============================
    ||  Private Auxiliary Methods  ||
    =================================*/

    /**
     * Prints supplied input wrapped with "______" separator.
     * The input is first formatted through {@link #encapsulateOutputWithSeparator(String)}.
     * @param input String to be displayed to the user.
     */
    private void displayToUser(String input) {
        System.out.println(encapsulateOutputWithSeparator(input));
    }

    /**
     * Takes an input String and wrap it with "______" separator before returning the new updated String.
     * @param input String to wrap the separators around.
     * @return String that is wrapped around the separators.
     */
    private String encapsulateOutputWithSeparator(String input) {
        sb.setLength(0);
        sb.append("\t" + SEPARATOR + "\n").append("\t " + input + "\n").append("\t" + SEPARATOR + "\n");
        return sb.toString();
    }

    /**
     * Initialize resources to be used by this DukeLogic class. Class-scope variables are instantialized in this method
     * as it will be ran before accepting user-input for commands.
     */
    private void initializeResources() {
        sb = new StringBuilder();
        scanner = new Scanner(System.in);
        userDukeTasks = new ArrayList<>(DUKE_MAXIMUM_TASKS);
    }

    /**
     * Reads in user-input as a String before checking the input. If the command is to terminate the program,
     * {@link #terminateProgram()} will be called. If the command is to list the tasks, {@link #displayDukeTasks()}
     * will be called. Otherwise, the user-input will be added to the list of input via {@link #addToDukeTasks(String)}
     */
    private void handleUserInput() {
        String input = scanner.nextLine();
        String[] inputTokens = input.split(" ");
        if (input.equals(DUKE_EXIT_COMMAND)) {
            terminateProgram();
        } else if (input.equals(DUKE_LIST_COMMAND)) {
            displayDukeTasks();
        } else if (inputTokens[0].equals(DUKE_DONE_COMMAND) && inputTokens.length > 1) {
            markDukeTaskComplete(Integer.parseInt(inputTokens[1]));
        } else {
            addToDukeTasks(input);
        }
    }

    /**
     * Displays the user-supplied list of tasks in a formatted style. This method will prepare the list by looping
     * through the List of tasks and printing each task with its index. Then it will call {@link #displayToUser(String)}
     * to display the final formatted list.
     */
    private void displayDukeTasks() {
        sb.setLength(0);
        sb.append("Here are the tasks in your list:\n\t ");
        for (int index = 0; index < userDukeTasks.size(); index++) {
            sb.append((index + 1) + "." + userDukeTasks.get(index).toString());
            if (index != (userDukeTasks.size() - 1)) {
                sb.append("\n\t ");
            }
        }
        displayToUser(sb.toString());
    }

    /**
     * Checks if the specified task index has already been marked as complete. If it is not then mark the task as
     * complete and print out the name of this task.
     * @param taskIndex Index of the task following the printed list from running the "list" command.
     */
    private void markDukeTaskComplete(int taskIndex) {
        DukeTask completedTask =  userDukeTasks.get(taskIndex - 1);
        sb.setLength(0);
        if (completedTask.getTaskIsComplete()) {
            sb.append("This task has already been marked as done!");
        } else {
            completedTask.setTaskComplete();
            sb.append("Nice! I've marked this task as done:\n\t   " + completedTask.toString());
        }
        displayToUser(sb.toString());
    }

    /**
     * Creates a new DukeTask and adds it into the current list of user DukeTask. The specified input is also mirrored
     * to the user.
     * @param input User specified input that will be the name of the DukeTask to be added to the current list of
     *              DukeTask.
     */
    private void addToDukeTasks(String input) {
        userDukeTasks.add(new DukeTask(input));
        displayToUser("added: " + input);
    }

    /**
     * Performs cleanup (if any) and prints the Duke exit message. Program then exits.
     */
    private void terminateProgram() {
        displayToUser(DUKE_EXIT_MESSAGE);
        System.exit(0);
    }

    /*===============================
    ||       Public Methods        ||
    =================================*/

    /**
     * Method first initializes the resources needed for this class before displaying the Duke ASCII logo and the
     * welcome message to the user through {@link #displayToUser(String)}.
     */
    public void displayWelcomeMessage() {
        initializeResources();
        System.out.println(DUKE_ASCII_LOGO);
        displayToUser(DUKE_WELCOME_MESSAGE);
    }

    /**
     *  Method will continuously read user-input with {@Link #handleUserInput()} until the terminating command is read.
     */
    public void runUntilBye() {
        while (true) {
            handleUserInput();
        }
    }

}
