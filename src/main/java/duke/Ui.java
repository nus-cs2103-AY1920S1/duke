package duke;

import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.io.IOException;
import java.util.Scanner;

/**
 * Represents the User Interface object which handles user interactions.
 */
public class Ui {

    private Scanner sc = new Scanner(System.in);

    /**
     * Reads a line of user input.
     * 
     * @return a line of user input
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints out loading error.
     */

    public void showLoadingError() {
        System.out.println("File not found");
    }

    /**
     * Prints welcome message.
     */

    public void showWelcomeMessage() {
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm \n" + logo);
        System.out.println("What can I do for you?");
    }

    /**
     * Prints the TaskList.
     * 
     * @param tasks TaskList to be printed
     */

    public void showTasks(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        System.out.println(tasks);
    }

    /**
     * Prints a line.
     */

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints goodbye message.
     */

    public void showGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints exception arising from running Duke.
     * 
     * @param ex Duke exception to be printed
     */

    public void showDukeError(DukeException ex) {
        System.out.println(ex);
    }

    /**
     * Prints the completion of a Task.
     * 
     * @param doneTask Task that is completed
     */

    public void showDoneTask(Task doneTask) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + doneTask);
    }

    /**
     * Prints that task has been successfully been added.
     * 
     * @param newTask      Task that has been added successfully
     * @param taskListSize Current number of tasks in TaskList
     */

    public void showAddTask(Task newTask, int taskListSize) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newTask);
        System.out.println("Now you have " + taskListSize + " tasks in the list.");
    }

    /**
     * Prints exceptions arisen from file management when running Duke.
     * 
     * @param e IO Exception to be printed
     */
    public void showIoException(IOException e) {
        System.out.println("Something went wrong: " + e.getMessage());
    }

    /**
     * Prints the successful deletion of task.
     * 
     * @param toRemove     task to be deleted
     * @param taskListSize Current size of TaskList after deletion
     */

    public void showDeleteTask(Task toRemove, int taskListSize) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + toRemove);
        System.out.println("Now you have " + taskListSize + " tasks in the list.");
    }

}
