import java.util.Scanner;
import java.util.ArrayList;

/**
 * Deals with interaction with the user.
 * Mainly printing to console appropriate messages and information (program status)
 * of specified format and indentation, according to situation.
 * Also responsible for reading in user inputs.
 */
public class Ui {

    private Scanner input;
    public Ui() {
        input = new Scanner(System.in);
    }

    ////////////////
    // CONSTANTS //
    //////////////
    // TODO make strings universal
    // TODO package classes
    private static final String STR_INDENT = "    ";
    private static final String STR_BORDER = STR_INDENT + "___________________________________";

    //////////////////
    // USER INPUTS //
    ////////////////

    /**
     * Returns text that user has entered in console
     * @return User input as string
     */
    public String readUserInput() {
        return input.nextLine();
    }

    //////////////////////
    // CONSOLE OUTPUTS //
    ////////////////////

    public void showGreetings() {
        String greeting = joinWithNewLines("Hello! I'm Duke", "What can I do for you?");
        showMessage(greeting);
    }

    public void showGoodbye() {
        String goodbye = indentMessage("Bye. Hope to see you again soon!");
        showMessage(goodbye);
    }

    public void showAddTaskResponse(Task addedTask, ArrayList<Task> taskArr) {
        String message = joinWithNewLines("Noted. I've added this task",
                addedTask.toString(),
                numTasksLeftMessage(taskArr));
        showMessage(message);
    }

    public void showDeleteTaskResponse(Task deletedTask, ArrayList<Task> taskArr) {
        String message = joinWithNewLines("Noted. I've removed this task",
                deletedTask.toString(),
                numTasksLeftMessage(taskArr));
        showMessage(message);
    }

    public void showMarkTaskDoneResponse(Task doneTask) {
        String message = joinWithNewLines("Nice! I've marked this task as done:",
                doneTask.toString());
        showMessage(message);
    }

    public void showListCommandResponse(ArrayList<Task> taskArr) {
        String response = joinWithNewLines("Here are the tasks in your list:",
                listOfTasksMessage(taskArr));
        showMessage(response);
    }

    public void showFindKeywordResponse(ArrayList<Task> taskArr) {
        String message = joinWithNewLines("Here are the matching tasks in your list:",
                listOfTasksMessage(taskArr));
        showMessage(message);
    }

    /**
     * Ordered list of tasks in string.
     * Includes their index in list, done or not, description etc.
     * Presents each task in a new line.
     * Format:
     * 1. [T][x] task-description
     * 2. [E][v] task-description (at: venue)
     * 3. [D][x] task-description (by: date)
     * @param taskArr ArrayList of Task objects
     * @return Message in a single string
     */
    private String listOfTasksMessage(ArrayList<Task> taskArr) {
        String output = "";
        for (Task task : taskArr) {
            int taskIdx = taskArr.indexOf(task)+1;
            output += indentMessage(taskIdx + "." +
                    task.toString());
        }
        return output;
    }

    private String numTasksLeftMessage(ArrayList<Task> taskArr) {
        return ("Now you have " + taskArr.size() +
                (taskArr.size() == 1? " task":" tasks") +
                " in the list.");
    }

    /////////////////////
    // HELPER METHODS //
    ///////////////////

    private String indentMessage(String message) {
        return STR_INDENT + message;
    }

    /**
     * Indents and ends each line in message with a "\n"
     * @param message Using varargs, each argument is a line in the message
     * @return Final message to be output to console, in a single string
     */
    private String joinWithNewLines(String... message) {
        String output = "";
        int numLines = 1;
        for (String line : message) {
            // Add new line if not last sentence in message
            if (numLines != message.length) {
                output += indentMessage(line + "\n");
                numLines++;
            } else {
                output += indentMessage(line);
            }
        }
        return output;
    }

    /**
     * Sandwiches text between lines, prints it.
     * @param message
     */
    public void showMessage(String message) {
        System.out.println(STR_BORDER);
        System.out.println(message);
        System.out.println(STR_BORDER);
    }

}
