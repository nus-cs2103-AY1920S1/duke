package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

/**
 * A user interface that handles the user input and interactions with the user.
 */
public class Ui {

    private void showLine() {
        System.out.println("\t____________________________________________________________");
    }

    /**
     * Prints the Duke logo and the welcome message. This method is called when the Parser starts
     * scanning the user's input.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        showLine();
        System.out.println("\t Hello! I'm Duke");
        System.out.println("\t What can I do for you?");
        showLine();
    }

    /**
     * Prints the farewell message. This method is called when Parser is done scanning the user's
     * input.
     */
    public void showBye() {
        showLine();
        System.out.println("\t Bye. Hope to see you again soon!");
        showLine();
    }

    /**
     * Prints the error message contained within the exception.
     *
     * @param errorMsg the error message to be printed.
     */
    public void showError(String errorMsg) {
        showLine();
        System.out.printf("\t ☹ OOPS!!! %s\n", errorMsg);
        showLine();
    }

    /**
     * Prints the entire list in the TaskList.
     *
     * @param list the list to be printed.
     */
    public void showList(ArrayList<Task> list) {
        int count = 1;
        showLine();
        System.out.println("\t Here are the task(s) in your list:");
        for (Task task: list) {
            System.out.printf("\t %d. %s\n", count, task);
            count++;
        }
        showLine();
    }

    /**
     * Prints the Task that has been added into the TaskList and also the number of remaining Tasks in
     * the TaskList.
     *
     * @param addedTask the Task that has been added.
     * @param taskList the TaskList which the Task is added to.
     */
    public void showAddedTask(Task addedTask, TaskList taskList) {
        showLine();
        System.out.println("\t Got it. I've added this task:");
        System.out.printf("\t   %s\n", addedTask);
        System.out.printf("\t Now you have %d task(s) in the list.\n", taskList.getSize());
        showLine();
    }

    /**
     * Prints the Task that has been deleted from the TaskList and also the number of remaining Tasks in
     * the TaskList.
     *
     * @param deletedTask the Task that has been deleted.
     * @param taskList the TaskList which the Task is deleted from.
     */
    public void showDeletedTask(Task deletedTask, TaskList taskList) {
        showLine();
        System.out.println("\t Noted. I've removed this task:");
        System.out.printf("\t   %s\n", deletedTask);
        System.out.printf("\t Now you have %d task(s) in the list.\n", taskList.getSize());
        showLine();
    }

    /**
     * Prints the Task after it has been completed.
     *
     * @param completed the Task that has been completed.
     */
    public void showCompletedTask(Task completed) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Nice! I've marked this task as done: ");
        System.out.printf("\t   %s\n", completed.toString());
        System.out.println("\t____________________________________________________________");
    }
}
