package duke.ui;

import duke.Duke;
import duke.utilities.Storage;
import duke.task.TaskList;
import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Helper class for displaying user interface messages
 */
public class MessageHandler {

    private TaskList tasks;
    private List<Task> taskList;
    private Storage storage;

    /**
     * Default constructor for UI class
     *
     * @param tasks   A <code>TaskList</code> object
     * @param storage A <code>Storage</code> object
     */
    public MessageHandler(TaskList tasks, Storage storage) {
        this.tasks = tasks;
        this.taskList = tasks.getList();
        this.storage = storage;
    }

    /**
     * Prints goodbye message to console
     */
    public String byeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints hello message to console
     * and retrieves previously saved state from local file
     */
    public String hiMessage() {
        String message = "Hello! I'm Duke.\n";

        try {
            storage.readFromTasksFileToList(tasks);
            message += "This is where you left off previously:\n";
        } catch (FileNotFoundException e) {
            message += "Fetching failed. " + e.getMessage() + "\n";
            message += "Creating file now...\n";
            File dukeTxt = new File(Duke.filePath);
            try {
                dukeTxt.createNewFile();
                message += "File created! " + dukeTxt.getAbsolutePath() + "\n";
                message += "Reading file...\n";
            } catch (IOException ioe) {
                message += "\t File creation was not successful. \n" ;
                message += "\t Exiting system.";
                System.exit(-1);
            }

        }

        message += getAllTasksAsString();

        assert !message.isEmpty();

        return message;
    }



    /**
     * Prints addTask success message
     *
     * @param task Task that was successfully added
     */
    public String addTaskConfirmationMessage(Task task) {
        String pluralOrNot = taskList.size() == 1 ? "task" : "tasks";
        List<Task> list = tasks.getList();
        String message = "Got it. I've added this task: \n" +
                "\t  " + task.toString() + "\n" +
                "Now you have " + list.size() + " " + pluralOrNot + " in the list";

        return message;
    }

    /**
     * Prints list of <code>Task</code> objects
     */
    public String getAllTasksAsString() {
        String taskListString;
        if (taskList.size() == 0) taskListString = "No pending tasks";
        else {
            String pluralOrNot = taskList.size() == 1 ? "Task" : "Tasks";
            taskListString = pluralOrNot + "\n";
            for (int i = 0; i < taskList.size(); i++) {
                taskListString += "\t " + (i + 1) + ".";
                taskListString += taskList.get(i) + "\n";
            }
        }

        assert !taskListString.isEmpty();

        return taskListString;
    }

    /**
     * Prints markTaskAsCompleted success message
     *
     * @param task Task that was successfully marked as completed
     */
    public String printMarkTaskAsCompletedMessage(Task task) {
        return "Nice! I've marked this task as done: \n" +
                "\t  [T] " + task.getTaskName() + " " + task.getDetails();
    }

    /**
     * Prints deleteTask success message
     *
     * @param task Task that was successfully deleted
     */

    public String taskDeletedConfirmationMessage(Task task) {
        String pluralOrNot = taskList.size() == 1 ? "task" : "tasks";
        // getting 'T' or 'F'
        char isCompleted = ((Boolean) task.isCompleted()).toString().toUpperCase().charAt(0);

        assert isCompleted == 'T' || isCompleted == 'F';

        return "I've removed this task: \n" +
                "\t  [" + isCompleted + "]" + task.getTaskName() + " " + task.getDetails() + "\n" +
                "Now you have " + taskList.size() + " " + pluralOrNot + " in the list";
    }

    public String searchResults(List<Task> taskList) {
        String results;
        if (taskList.isEmpty()) {
            results = "You have no tasks that match the search query";
        } else {
            results = "Here are your search results: \n";
            for (int i = 0; i < taskList.size(); i++) {
                results += "\t " + (i + 1) + ".";
                results += (taskList.get(i) + "\n");
            }
        }

        assert !results.isEmpty();
        return results;
    }
}
