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
    private StringBuilder sb;


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
     * @param taskList that contains the last task.
     */
    public String showAddedTask(TaskList taskList) {
        sb = new StringBuilder();

        sb.append("Got it. I've added this task: ");
        sb.append(System.lineSeparator());

        sb.append(String.format("    %s", taskList.getLast().toString()));
        sb.append(System.lineSeparator());

        sb.append(String.format("Now you have %d tasks in the list.", taskList.getSize()));
        sb.append(System.lineSeparator());

        return sb.toString();
    }

    /**
     * Prints out the entire taskList.
     * @param taskList that contains all the tasks.
     */
    public String showTaskList(TaskList taskList) {

        sb = new StringBuilder();
        sb.append("Here are the tasks in your list:");
        sb.append(System.lineSeparator());

        ArrayList<Task> arr = taskList.getArr();
        int index  = 1;
        for (Task task : arr) {
            sb.append(String.format("%d. %s", index, task.toString()));
            sb.append(System.lineSeparator());
            index++;
        }
        return sb.toString();
    }

    /**
     * Prints out all the tasks that matches.
     * @param arr that contains found tasks.
     */
    public String showMatchingTasks(ArrayList<Task> arr) {
        sb = new StringBuilder();

        sb.append("Here are the matching tasks in your list:");
        sb.append(System.lineSeparator());

        int index  = 1;
        for (Task task : arr) {
            sb.append(String.format("%d. %s", index, task.toString()));
            sb.append(System.lineSeparator());
            index++;
        }
        return sb.toString();
    }

    /**
     * Prints out the task that is recently deleted.
     * @param taskList that contains size of array.
     * @param t shows that task that is deleted.
     */
    public String showDeletedTask(TaskList taskList, Task t) {
        sb = new StringBuilder();
        sb.append("Noted. I've removed this task: ");
        sb.append(System.lineSeparator());

        sb.append(String.format("    %s", t));
        sb.append(System.lineSeparator());

        sb.append(String.format("Now you have %d tasks in the list.", taskList.getArr().size()));
        sb.append(System.lineSeparator());
        return sb.toString();
    }

    /**
     * Prints welcome message.
     */
    public String showWelcome()  {
        sb = new StringBuilder();
        sb.append("Hello I'm Pikachu! \nWhat can I do for you?");
        sb.append(System.lineSeparator());
        return sb.toString();
    }


    /**
     * Prints error message.
     * @param errorMsg error message String.
     */
    public String showError(String errorMsg) {
        sb = new StringBuilder();
        sb.append(errorMsg);
        sb.append(System.lineSeparator());
        return sb.toString();
    }

    /**
     * Prints out the Task t marked as done.
     * @param  t Task to be marked as done.
     */
    public String showDoneTask(Task t) {
        sb = new StringBuilder();
        sb.append("Nice! I've marked this task as done");
        sb.append(System.lineSeparator());

        sb.append(String.format("    %s", t));
        sb.append(System.lineSeparator());

        return sb.toString();
    }

    /**
     * Prints out dividing line.
     */
    public String showLine() {
        sb = new StringBuilder();

        sb.append("____________________________________________________________");
        sb.append(System.lineSeparator());

        return sb.toString();
    }

    /**
     * Prints out goodbye message.
     */
    public String showGoodbye() {
        sb = new StringBuilder();
        sb.append("Bye. Hope to see you again!");
        sb.append(System.lineSeparator());

        return sb.toString();
    }


    /**
     * Prints out priority change message.
     * @param task to print.
     * @return String output.
     */
    public String showPriorityChange(Task task) {
        sb = new StringBuilder();
        sb.append("Nice! I've changed the priority of this task!");
        sb.append(System.lineSeparator());

        sb.append(String.format("    %s", task));
        sb.append(System.lineSeparator());

        return sb.toString();
    }
}
