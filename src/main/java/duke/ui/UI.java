package duke.ui;

import duke.exception.DukeException;
import duke.task.Task;
import duke.tasklist.TaskList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Class that represents all the methods used to print out messages.
 */
public class UI {

    /**
     * The main list of tasks.
     */
    private TaskList taskList;

    /**
     * The reader used to read the user input.
     */
    private BufferedReader reader;

    /**
     * Constructor that takes in list of tasks to be printed.
     * @param list The main list of tasks.
     */
    public UI(TaskList list) {
        this.taskList = list;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Used to read the user input.
     * @return The user input in String.
     * @throws IOException Used for when the reader has any errors.
     */
    public String readLine() throws IOException {
        return reader.readLine();
    }

    /**
     * Used to print the lines for partition.
     */
    public void printLine() {
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Used to print out an empty line.
     */
    public void printBlank() {
        System.out.println();
    }

    /**
     * Used to print out list of tasks.
     * @param task The main list of tasks to be printed out.
     */
    public void printTask(Task task) {
        System.out.println("       " + task);
    }

    /**
     * Prints the introduction of the program during startub.
     */
    public void printIntro() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printLine();
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        printLine();
        printBlank();
    }

    /**
     * Used to print out error messages.
     * @param error The error message to be printed out.
     */
    public void printError(DukeException error) {
        printLine();
        System.out.println(error);
        printLine();
        printBlank();
    }

    /**
     * Used to print out indication that a new task has been added.
     * @param task The task that is added.
     */
    public void printTaskAdd(Task task) {
        printLine();
        System.out.println("     Got it. I've added this task:");
        printTask(task);
        if (taskList.size() == 1) {
            System.out.println("     Now you have " + taskList.size() + " task in the list");
        } else {
            System.out.println("     Now you have " + taskList.size() + " tasks in the list");
        }
        printLine();
        printBlank();
    }

    /**
     * Used to print out indication that a task has been deleted.
     * @param task The task that is deleted.
     */
    public void printTaskDelete(Task task) {
        printLine();
        System.out.println("     Noted. I have removed this task:");
        printTask(task);
        if (taskList.size() == 1) {
            System.out.println("     Now you have " + taskList.size() + " task in the list");
        } else {
            System.out.println("     Now you have " + taskList.size() + " tasks in the list");
        }
        printLine();
        printBlank();
    }

    /**
     * Used to print out indication that the task has been completed.
     * @param task The task that is completed.
     */
    public void printTaskDone(Task task) {
        printLine();
        System.out.println("     Nice! I've marked this task as done:");
        printTask(task);
        printLine();
        printBlank();
    }

    /**
     * Used to print out the list of tasks.
     */
    public void printList() {
        printLine();
        System.out.println("     Here are the tasks in your list:");
        for (int i = 1; i <= taskList.size(); i++) {
            System.out.println("     " + i + ". " + taskList.get(i - 1));
        }
        printLine();
        printBlank();
    }

    /**
     * Used to print out the message when quitting the program.
     */
    public void printBye() {
        printLine();
        System.out.println("     Bye. Hope to see you again soon!");
        printLine();
        printBlank();
    }

}
