/**
 * Handles the interaction with user.
 * @author Fabian Chia Hup Peng
 */

import java.util.Scanner;

public class Ui {

    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String NEW_LINE = "\n";
    private static final String INTRO_MESSAGE = "Hello! I'm Duke!\n" + "What can I do for you?";
    private static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String ADDED_MESSAGE = "Got it. I've added this task:\n";
    private static final String DELETED_MESSAGE = "Noted. I've removed this task:\n";
    private static final String DONE_MESSAGE = "Nice! I've marked this task as done:\n";
    private static final String FOUND_MESSAGE = "Here are the matching tasks in your list:\n";
    private static final String HELP_MESSAGE = "Type one of the following commands:\n";
    private static final String TASKS_MESSAGE = "Here are the tasks in your list:\n";
    private static final String INVALID_MESSAGE = "Sorry, I don't recognise that!\n\nFor a list of commands: type 'help'";

    private Scanner scanner;

    /**
     * Creates a Ui instance with the appropriate attributes.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads the next line of user input.
     * @return The user input, in string representation.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Returns the Duke welcome message.
     * @return The string representation of the Duke welcome message.
     */
    public static String stringWelcomeMessage() {
        return INTRO_MESSAGE;
    }

    /**
     * Returns the Duke goodbye message.
     * @return The string representation of the Duke deleted message.
     */
    public String stringGoodbyeMessage() {
        return GOODBYE_MESSAGE;
    }

    /**
     * Returns the Duke added message.
     * @param task The task to be added.
     * @param taskList The task list to be added to.
     * @return The string representation of the Duke added message.
     */
    public String stringAddedMessage(Task task, TaskList taskList) {
        String message = ADDED_MESSAGE + task + NEW_LINE;

        int numTasks = taskList.getSize();

        if (numTasks == 1) {
            message += "Now you have " + numTasks + " task in the list.";
        } else {
            message += "Now you have " + numTasks + " tasks in the list.";
        }

        return message;
    }

    /**
     * Returns the Duke deleted message.
     * @param task The task to be deleted.
     * @param taskList The task list to be deleted from.
     * @return The string representation of the Duke deleted message.
     */
    public String stringDeletedMessage(Task task, TaskList taskList) {
        String message = DELETED_MESSAGE + task + NEW_LINE;

        int numTasks = taskList.getSize();

        if (numTasks == 1) {
            message += "Now you have " + numTasks + " task in the list.";
        } else {
            message += "Now you have " + numTasks + " tasks in the list.";
        }

        return message;
    }

    /**
     * Returns the Duke done message.
     * @param task The task to be set as done.
     * @return The string representation of the Duke done message.
     */
    public String stringDoneMessage(Task task) {
        String message = DONE_MESSAGE + task + NEW_LINE;
        return message;
    }

    /**
     * Returns the contents of a found list.
     * @param foundList The found list with contents to be returned.
     * @return The string representation of the contents of the found list.
     */
    public String stringFoundMessage(TaskList foundList) {
        String message = FOUND_MESSAGE + foundList.listTasks();
        return message;
    }

    /**
     * Returns all possible Duke commands.
     * @return The string representation of all possible Duke commands.
     */
    public String stringHelpMessage() {
        String message = HELP_MESSAGE + NEW_LINE + generatePossibleCommands();
        return message;
    }

    /**
     * Returns the Duke invalid message.
     * @param errorMessage The string representation of the error message.
     * @return The string representation of the Duke invalid message.
     */
    public String stringInvalidMessage(String errorMessage) {
        String message = INVALID_MESSAGE + NEW_LINE + errorMessage;
        return message;
    }

    /**
     * Returns the contents of a task list.
     * @param taskList The task list with contents to be returned.
     * @return The string representation of the contents of the task list.
     */
    public String stringTaskList(TaskList taskList) {
        String message = TASKS_MESSAGE + taskList.listTasks();
        return message;
    }

    /**
     * Returns a list of possible Duke commands.
     * @return The string representation of a list of possible Duke commands.
     */
    private String generatePossibleCommands() {
        String allCommands = "todo <taskName>\n"
                                + "\tAdds a todo task to the list\n"
                                + "deadline <taskName> / <dd/mm/yyyy> <24HrTime>\n"
                                + "\tAdds a deadline task to the list\n"
                                + "event <taskName> / <dd/mm/yyyy> <24HrTime>\n"
                                + "\tAdds an event task to the list\n"
                                + "list\n"
                                + "\tDisplays all tasks in the list\n"
                                + "find <keyWord>\n"
                                + "\tDisplays all tasks with <keyWord> in description\n"
                                + "delete <taskNumber>\n"
                                + "\tDeletes the task with matching <taskNumber> in list\n";
        return allCommands;
    }

}
