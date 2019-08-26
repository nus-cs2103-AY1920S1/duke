package duke.ui;

import duke.logic.TaskList;
import duke.task.Task;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Controls what the user sees on the Command Line.
 */

public class Ui {

    private Scanner sc;

    /**
     * Constructor.
     * Initialize Scanner object.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Reads command from user input.
     * @return fullCommand
     */
    public String readCommand() {
        String fullCommand = sc.nextLine();
        return fullCommand;
    }

    /**
     * Prints out the last task in taskList.
     * @param taskList
     */
    public void showAddedTask(TaskList taskList) {
        System.out.println("Got it. I've added this task: ");
        System.out.println(String.format("    %s", taskList.getLast().toString()));
        System.out.println(String.format("Now you have %d tasks in the list.", taskList.getSize()));
    }

    /**
     * Prints out the entire taskList.
     * @param taskList
     */
    public void showTaskList(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");

        ArrayList<Task> arr = taskList.getArr();
        int index  = 1;
        for (Task task : arr) {
            System.out.println(String.format("%d. %s", index , task.toString()));
            index++;
        }
    }


    public void showFoundTasks(ArrayList<Task> arr) {
        System.out.println("Here are the matching tasks in your list:");
        int index  = 1;
        for (Task task : arr) {
            System.out.println(String.format("%d. %s", index , task.toString()));
            index++;
        }
    }

    /**
     * Prints out the task that is recently deleted.
     * @param taskList
     * @param t
     */
    public void showDeletedTask(TaskList taskList, Task t) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println(String.format("    %s", t));
        System.out.println(String.format("Now you have %d tasks in the list.", taskList.getArr().size()));
    }

    /**
     * Prints welcome message.
     */
    public void showWelcome()  {
        System.out.println("Hello I'm Duke! \nWhat can I do for you?");
    }


    /**
     * Prints error message.
     * @param errorMsg
     */
    public void showError(String errorMsg) {
        System.out.println(errorMsg);
    }

    /**
     * Prints out the Task t marked as done.
     * @param  t
     */
    public void showDoneTask(Task t) {
        System.out.println("Nice! I've marked this task as done");
        System.out.println(String.format("    %s", t));
    }

    /**
     * Prints out dividing line.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints out goodbye message.
     */
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again!");
    }


}
