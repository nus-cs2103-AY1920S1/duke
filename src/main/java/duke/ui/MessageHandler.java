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
 * Helper class for displaying user interface messages.
 */
public class MessageHandler {

    private TaskList tasks;
    private List<Task> taskList;
    private Storage storage;

    /**
     * Default constructor for UI class.
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
     * Goodbye message.
     *
     * @return <code>String</code> goodbye message
     */
    public String byeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Hello message and retrieves previously saved state from local file.
     *
     * @return <code>String</code> hello message
     */
    public String hiMessage() {
        return "Hello! I'm Duke.\n";
    }


    /**
     * Add task success message.
     *
     * @param task Task that was successfully added
     * @return <code>String</code> task added confirmation message
     */
    public String addTaskConfirmationMessage(Task task) {
        String pluralOrNot = taskList.size() == 1 ? "task" : "tasks";
        List<Task> list = tasks.getList();
        return "Got it. I've added this task: \n"
                + "\t  " + task.toString() + "\n"
                + "Now you have " + list.size() + " " + pluralOrNot + " in the list";

    }

    /**
     * <code>String</code> of all tasks in list.
     *
     * @return All tasks in <code>String</code> format
     */
    public String getAllTasksAsString() {
        String taskListString;
        if (taskList.size() == 0) {
            taskListString = "No pending tasks";
        } else {
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
     * Mark task as completed success message.
     *
     * @param task Task that was successfully marked as completed
     * @return <code>String</code> task marked as completed success message
     */
    public String printMarkTaskAsCompletedMessage(Task task) {
        return "Nice! I've marked this task as done: \n"
                + "\t  " + task.toString() + "\n";
    }

    /**
     * DeleteTask success message.
     *
     * @param task Task that was successfully deleted
     * @return <code>String</code> task successfully deleted message
     */
    public String taskDeletedConfirmationMessage(Task task) {
        String pluralOrNot = taskList.size() == 1 ? "task" : "tasks";
        return "I've removed this task: \n"
                + "\t  " + task.toString() + "\n"
                + "Now you have " + taskList.size() + " " + pluralOrNot + " in the list";
    }


    /**
     * List of search results.
     *
     * @param taskList list of search results
     * @return <code>String</code> list of search results
     */
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

    /**
     * Tag task success message.
     *
     * @param task task that was tagged
     * @return <code>String</code> task successfully tagged message
     */
    public String tagAddedConfirmationMessage(Task task) {
        return "I've tagged this task: \n"
                + "\t  " + task.toString() + "\n";
    }
}
