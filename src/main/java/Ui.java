import java.util.ArrayList;

/**
 * Interacts with user by providing messages when required.
 */
public class Ui {
    public static String hr = "______________________________________________________________________";

    /**
     * Prints welcome when Duke is run.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        this.printLine();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        this.printLine();
    }

    /**
     * Prints goodbye when 'bye' command executed.
     */
    public void showGoodBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints exception's message.
     * @param e exception that needs to be printed.
     */
    public void showException(Exception e) {
        System.err.println(e.getMessage());
    }

    /**
     * Prints particular type of error message when non-number input is provided where number is required.
     * @param type type of command for which the error message needs to be generated.
     */
    public void showNumberFormatError(String type) {
        switch (type) {
        case "done" : System.err.println(" :( OOPS!!! Invalid format."
                + " Please enter the number of the task to be marked as done.");
            break;
        case "delete" : System.err.println(" :( OOPS!!! Invalid format."
                + " Please enter the number of the task to be deleted.");
            break;
        default : System.err.println(" :( OOPS!!! Invalid format."
            + " Please enter a number.");
        }
    }

    /**
     * Prints a long horizontal line for segmentation of commands that user provides and output that user sees.
     */
    public void printLine() {
        System.out.println(hr);
    }

    /**
     * Prints a message providing details about task just created.
     * @param task the task that is created.
     * @param noOfTasks number of tasks at the time, usually from TaskList.
     */
    public void showTaskCreated(Task task, int noOfTasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + task);
        System.out.println("Now you have " + noOfTasks + " tasks in the list.");
    }

    /**
     * Prints a message providing details about the task just marked as done.
     * @param task task that is marked as done.
     */
    public void showTaskDone(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
    }

    /**
     * Prints a message providing details about the task just deleted and remaining tasks in TaskList.
     * @param task task that is just deleted.
     * @param noOfTasks number of tasks in TaskList
     */
    public void showTaskDeleted(Task task, int noOfTasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(" " + task);
        System.out.println("Now you have " + noOfTasks + " tasks in the list.");
    }

    /**
     * Prints output for the 'list' command
     * @param taskList the taskList that needs its Tasks' details printed.
     */
    public void showTasks(TaskList taskList) {
        ArrayList<Task> arr = taskList.getArr();
        for (int i = 0; i < arr.size(); i++) {
            Task temp = arr.get(i);
            System.out.println((i + 1) + ". " + temp);
        }
        if (taskList.isEmpty()) {
            System.out.println("There are no Tasks to show.");
        }
    }

    /**
     * Prints error message if loading history file encounters problem.
     */
    public void showLoadingError() {
        System.err.println(" :( OOPS!!! Error occurred while loading the history file.");
    }
}
