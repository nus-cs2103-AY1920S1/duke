package duke;

import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Helper class for displaying user interface messages
 */
public class UI {

    private TaskList tasks;
    private List<Task> taskList;
    private Storage storage;

    /**
     * Default constructor for UI class
     *
     * @param tasks   A <code>TaskList</code> object
     * @param storage A <code>Storage</code> object
     */
    public UI(TaskList tasks, Storage storage) {
        this.tasks = tasks;
        this.taskList = tasks.getList();
        this.storage = storage;
    }

    /**
     * Prints goodbye message to console
     */
    public void sayBye() {
        System.out.println("\t Bye. Hope to see you again soon!");
    }

    /**
     * Prints hello message to console
     * and retrieves previously saved state from local file
     */
    public void sayHi() {
        System.out.println("\t Hello! I'm Duke.");

        try {
            storage.readFromTasksFileToList(tasks);
            System.out.println("\t This is where you left off previously: ");
            printLine();
        } catch (FileNotFoundException e) {
            System.out.println("\t Fetching failed. " + e.getMessage());
            System.out.println("\t Creating file now...");
            File dukeTxt = new File(Duke.filePath);
            try {
                dukeTxt.createNewFile();
                System.out.println("\t File created! " + dukeTxt.getAbsolutePath());
                System.out.println("\t Reading file...");
                printLine();
            } catch (IOException ioe) {
                System.out.println("\t File creation was not successful. " + ioe);
                System.out.println("\t Exiting system.");
                printLine();
                System.exit(-1);
            }

        }
        printTasks();
        printLine();
    }

    /**
     * Prints line separator to console
     */
    public static void printLine() {
        System.out.println("\t_______________________________");
    }


    /**
     * Prints addTask success message
     *
     * @param task Task that was successfully added
     */
    public void printAddTaskMessage(Task task) {
        String pluralOrNot = taskList.size() == 1 ? "task" : "tasks";
        List<Task> list = tasks.getList();
        System.out.println("\t Got it. I've added this task: \n" +
                "\t  " + task.toString() + "\n" +
                "\t Now you have " + list.size() + " " + pluralOrNot + " in the list");
    }

    /**
     * Prints list of <code>Task</code> objects
     */
    public void printTasks() {
        if (taskList.size() == 0) System.out.println("\t No pending tasks");
        else {
            String pluralOrNot = taskList.size() == 1 ? "Task" : "Tasks";
            System.out.println("\t " + pluralOrNot);
            for (int i = 0; i < taskList.size(); i++) {
                System.out.print("\t " + (i + 1) + ".");
                System.out.println(taskList.get(i));
            }
        }
    }

    /**
     * Prints markTaskAsCompleted success message
     *
     * @param task Task that was successfully marked as completed
     */
    public void printMarkTaskAsCompletedMessage(Task task) {
        System.out.println("\t Nice! I've marked this task as done: \n" +
                "\t  [✓] " + task.getTaskName() + " " + task.getDetails());
    }

    /**
     * Prints deleteTask success message
     *
     * @param task Task that was successfully deleted
     */
    public void printDeleteTaskMessage(Task task) {
        String pluralOrNot = taskList.size() == 1 ? "task" : "tasks";
        System.out.println("\t I've removed this task: \n" +
                "\t  [✓] " + task.getTaskName() + " " + task.getDetails() + "\n" +
                "\t Now you have " + taskList.size() + " " + pluralOrNot + " in the list");
    }
}
