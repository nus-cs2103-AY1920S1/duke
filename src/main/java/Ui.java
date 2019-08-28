import java.util.Scanner;
import java.util.ArrayList;

/**
 * Deals with interaction with the user.
 * Mainly printing to console appropriate messages and information (program status)
 * of specified format and indentation, according to situation.
 * Also responsible for reading in user inputs.
 */
public class Ui {

    private Scanner scanner;
    public Ui() {
        scanner = new Scanner(System.in);
    }

    ///////////////
    // MESSAGES //
    /////////////
    private String INDENT_STR = "    ";
    private String BORDER_STR = "-----";

    //////////////////
    // USER INPUTS //
    ////////////////

    /**
     * Returns text that user has entered in console
     * @return User input as string
     */
    public String readUserInput() {
        return scanner.nextLine();
    }

    //////////////////////
    // CONSOLE OUTPUTS //
    ////////////////////

    public void showGreetings() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println("=====");
    }

    public void showGoodbye() {
        showMessage("Bye. Hope to see you again soon!");
    }

    /**
     * Prints out information of tasks currently in list.
     * Includes their order, done or not, description etc.
     * Presents each task in a new line.
     * @param taskList
     */
    public void showAllTasks(TaskList taskList) {
        ArrayList<Task> taskArr = taskList.getTaskArr();
        System.out.println(BORDER_STR);
        System.out.println(INDENT_STR + "Here are the tasks in your list:");
        showTasksInList(taskArr);
        System.out.println(BORDER_STR);
    }

    public void showTasksInList(ArrayList<Task> taskArr) {
        for (Task task : taskArr) {
            // Format: 1. [T/D/E][v/x] task-description (by/at: ...)
            System.out.println(INDENT_STR +
                    (taskArr.indexOf(task)+1) + "." +
                    task.toString());
        }
    }

    public void showAddTaskMessage(Task addedTask, ArrayList<Task> taskArr) {
        modifiedTaskListMessage("Noted. I've added this task:",
                addedTask, taskArr);
    }

    public void showDeleteTaskMessage(Task deletedTask, ArrayList<Task> taskArr) {
        modifiedTaskListMessage("Noted. I've removed this task:",
                deletedTask, taskArr);
    }

    public void showMatchingKeywordTasks(ArrayList<Task> taskArr) {
        System.out.println(BORDER_STR);
        System.out.println(INDENT_STR + "Here are the matching tasks in your list:");
        showTasksInList(taskArr);
        System.out.println(BORDER_STR);
    }

    public void showMarkTaskDoneMessage(Task doneTask) {
        System.out.println(BORDER_STR);
        System.out.println(indentMessage("Nice! I've marked this task as done:"));
        System.out.println(indentMessage("  " + doneTask.toString()));
        System.out.println(BORDER_STR);
    }

    private void modifiedTaskListMessage(String message, Task task, ArrayList<Task> taskArr) {
        System.out.println(BORDER_STR);
        System.out.println(indentMessage(message));
        System.out.println(indentMessage("  " + task.toString()));
        // Show current number of tasks in list
        System.out.println(indentMessage(
                "Now you have " + taskArr.size() +
                        (taskArr.size() == 1? " task":" tasks") +
                        " in the list."));
        System.out.println(BORDER_STR);
    }

    /////////////////////
    // HELPER METHODS //
    ///////////////////

    private String indentMessage(String message) {
        return INDENT_STR + message;
    }

    /**
     * Sandwiches text between -----s
     * @param message
     */
    public void showMessage(String message) {
        System.out.println(BORDER_STR);
        System.out.println(indentMessage(message));
        System.out.println(BORDER_STR);
    }

}
