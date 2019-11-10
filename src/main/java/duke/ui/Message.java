package duke.ui;

import duke.exception.DukeException;
import duke.task.Task;
import duke.tasklist.TaskList;

/**
 * Message Class which handles all the messages which can be output to the user.
 */
public class Message {

    public static String WELCOME = "Feed me, human. \n"
            + "Then I might consider doing the following:\n"
            + "        tell me a joke\n"
            + "        todo [desc]\n"
            + "        sort [category]\n";

    public static String EXIT = "Bye. Hope to never see you again!";
    public static String EMPTY_LIST = "No tasks on this list (except feeding me)";
    public static String LOADING_ERROR = "No existing data. Duke initialised with an empty TaskList";
    public static String GENERAL_INVALID_INPUT = "Instead of wasting time typing gibberish, why don't you make "
            + "yourself useful and feed me.";
    public static String CLEAR_SUCCESS = "Your tasks have been cleared.";

    /**
     * Shows the list of tasks.
     * @param taskList TaskList that is to be shown.
     * @throws DukeException Throws an exception if it is an invalid list or there exists and invalid task in the list.
     */
    public static String showList(TaskList taskList) throws DukeException {
        String list = "";
        for (int i = 0; i < taskList.getSize(); i++) {
            list += (i + 1) + ". " + taskList.get(i) + "\n";
        }

        return list;
    }

    /**
     * Shows a formatted error message.
     * @param errorMessage Error message that will be printed.
     */
    public static String showError(String errorMessage) {
        return errorMessage;
    }

    /**
     * Shows a success message when the sort is successfully sorted.
     * @param taskList Tasklist that is being sorted.
     * @return String of the success message and sorted tasks.
     * @throws DukeException Throws an exception if it is an invalid list or there exists and invalid task in the list.
     */
    public static String showSortSuccess(TaskList taskList) throws DukeException {
        return "Sort it yourself... Ok fine here it is: \n"
                + showList(taskList);
    }

    /**
     * Shows a message of the list of tasks found.
     * @param taskList Filtered Tasklist that will be displayed.
     * @return List of tasks found.
     * @throws DukeException Throws an exception if it is an invalid list or there exists and invalid task in the list.
     */
    public static String showFindSuccess(TaskList taskList) throws DukeException {
        return "Here are the matching tasks in your list:\n"
                + "Now where's my fish.\n"
                + showList(taskList);
    }

    /**
     * Shows a message when a task has been successfully added.
     * @param addedTask Task that is to be added.
     * @param taskList TaskList that the Task is to be added to.
     */
    public static String showAddSuccess(Task addedTask, TaskList taskList) {
        return "I've added this task:\n"
                + addedTask + "\n"
                + "Now you have " + taskList.getSize() + " tasks in this list.";
    }

    /**
     * Shows a message when a task has been successfully deleted.
     * @param deletedTask Task that is to be deleted.
     * @param taskList TaskList that the Task is to be deleted from.
     */
    public static String showDeleteSuccess(Task deletedTask, TaskList taskList) {
        return "I've removed this task, now kindly remove yourself from my sight:\n"
                + deletedTask + "\n"
                + "You have " + taskList.getSize() + " tasks in this list.";
    }

    /**
     * Shows a message when a task is successfully marked as done.
     * @param doneTask Task that is to be marked as done.
     */
    public static String showDoneSuccess(Task doneTask) {
        return "Finally doing something useful I see:\n"
                + doneTask;
    }

}
