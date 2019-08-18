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
    private final String DUKE_EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    private final String DUKE_UNKNOWN_COMMAND_MESSAGE = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    private final String SEPARATOR = "____________________________________________________________";
    private final String DUKE_EXIT_COMMAND = "bye";
    private final String DUKE_LIST_COMMAND = "list";
    private final String DUKE_DONE_COMMAND = "done";
    private final int DUKE_MAXIMUM_TASKS = 100;
    private enum DukeCommand {
        TODO, DEADLINE, EVENT, LIST, DONE, BYE
    }
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
     * Gets the starting index of the parameter of the supplied flag in the input String array.
     * @param inputTokens Array of String to check for if the flag exists.
     * @param flag Exact String to look for.
     * @return Starting index of the flag's parameter, -1 if flag is not found in the array.
     */
    private int getInputFlagParameterStartingIndex(String[] inputTokens, String flag) {
        for (int counter = 0; counter < inputTokens.length; counter++) {
            if (inputTokens[counter].equals(flag)) {
                return counter + 1;
            }
        }
        return -1;
    }

    /**
     * Concatenates the input String[] from the specified starting index, with a space delimiter between each token
     * with the exception of the last token. Returns the concatenated String.
     * @param inputTokens Input String array to concatenate from.
     * @param startIndex Starting index (inclusive) to start concatenating from.
     * @param endIndex Ending index (inclusive) to end concatenating at.
     * @return A concatenated String starting from the startIndex to the endIndex, with a single-space delimiter except
     * after the last token.
     */
    private String concatStringTokens(String[] inputTokens, int startIndex, int endIndex) {
        sb.setLength(0);
        while (startIndex < endIndex) {
            sb.append(inputTokens[startIndex++] + " ");
        }
        sb.append(inputTokens[startIndex]);
        return sb.toString();
    }

    /**
     * Reads in user-input as a String before checking the input. If the command is to terminate the program,
     * {@link #terminateProgram()} will be called. If the command is to list the tasks, {@link #displayDukeTasks()}
     * will be called. Otherwise, the user-input will be added to the list of input via {@link #addToDukeTasks(DukeTask
     * inputTask)}
     */
    private void handleUserInput() {
        String input = scanner.nextLine();
        String[] inputTokens = input.split(" ");
        try {
            DukeCommand inputCommand = DukeCommand.valueOf(inputTokens[0].toUpperCase());
            switch (inputCommand) {
                case BYE:
                    terminateProgram();
                    break;

                case TODO:
                    if (inputTokens.length == 1) {
                        displayToUser("☹ OOPS!!! The description of a todo cannot be empty.");
                    } else {
                        String todoTaskName = concatStringTokens(inputTokens, 1, (inputTokens.length - 1));
                        DukeTaskToDo dukeToDo = new DukeTaskToDo(todoTaskName);
                        addToDukeTasks(dukeToDo);
                    }
                    break;

                case DEADLINE:
                    if (inputTokens.length == 1) {
                        displayToUser("☹ OOPS!!! The description of a deadline cannot be empty.");
                    } else {
                        int deadlineParameterIndex = getInputFlagParameterStartingIndex(inputTokens, "/by");
                        if (deadlineParameterIndex == -1) {
                            displayToUser("☹ OOPS!!! The deadline for the task must be specified with \"/by\".");
                        } else {
                            String deadlineTaskName = concatStringTokens(inputTokens, 1,
                                    (deadlineParameterIndex - 2));
                            String deadlineParameterString = concatStringTokens(inputTokens, deadlineParameterIndex,
                                    (inputTokens.length - 1));
                            DukeTaskDeadline dukeDeadline = new DukeTaskDeadline(deadlineTaskName,
                                    deadlineParameterString);
                            addToDukeTasks(dukeDeadline);
                        }
                    }
                    break;

                case EVENT:
                    if (inputTokens.length == 1) {
                        displayToUser("☹ OOPS!!! The description of a deadline cannot be empty.");
                    } else {
                        int eventParameterIndex = getInputFlagParameterStartingIndex(inputTokens, "/at");
                        if (eventParameterIndex == -1) {
                            displayToUser("☹ OOPS!!! The event parameter must be specified with \"/at\".");
                        } else {
                            String eventTaskName = concatStringTokens(inputTokens, 1,
                                    (eventParameterIndex - 2));
                            String eventParameterString = concatStringTokens(inputTokens, eventParameterIndex,
                                    (inputTokens.length - 1));
                            DukeTaskEvent dukeEvent = new DukeTaskEvent(eventTaskName, eventParameterString);
                            addToDukeTasks(dukeEvent);
                        }
                    }
                    break;

                case LIST:
                    displayDukeTasks();
                    break;

                case DONE:
                    if (inputTokens.length == 2) {
                        try {
                            int taskIndex = Integer.parseInt(inputTokens[1]);
                            if (taskIndex < 1 || taskIndex >= userDukeTasks.size()) {
                                displayToUser("☹ OOPS!!! Please enter a valid task index value.");
                            } else {
                                markDukeTaskComplete(taskIndex);
                            }
                        } catch (NumberFormatException ex) {
                            displayToUser("☹ OOPS!!! Please only enter numeric values for the task index.");
                        }
                    } else {
                        displayToUser("☹ OOPS!!! The index of the completed task is missing.");
                    }
                    break;
            }
        } catch (IllegalArgumentException ex) {
            displayToUser(DUKE_UNKNOWN_COMMAND_MESSAGE);
        }
    }

    /**
     * Creates a new DukeTask and adds it into the current list of user DukeTask. The specified input is also mirrored
     * to the user.
     * @param inputTask User specified input that will be the name of the DukeTask to be added to the current list of
     *              DukeTask.
     */
    private void addToDukeTasks(DukeTask inputTask) {
        userDukeTasks.add(inputTask);
        sb.setLength(0);
        sb.append("Got it. I've added this task:\n\t   " + inputTask.toString() + "\n\t Now you have " +
                userDukeTasks.size() + " tasks in the list.");
        displayToUser(sb.toString());
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
     * Displays the Duke ASCII logo and the welcome message to the user through {@link #displayToUser(String)}.
     */
    public void displayWelcomeMessage() {
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

    /**
     * Method will first initialize the resources needed for this class. Then it will display the Duke welcome message,
     * followed by running until a terminating command is read.
     */
    public void run() {
        initializeResources();
        displayWelcomeMessage();
        runUntilBye();
    }
}
