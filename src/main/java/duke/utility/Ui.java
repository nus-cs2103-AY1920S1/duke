package duke.utility;

import duke.errands.Task;
import duke.exception.DukeException;
import java.util.ArrayList;

public class Ui {

    /**
     * Returns a string that will be used as a response to "list" command.
     *
     * @param tasks Task list of all of Duke's current tasks.
     * @return String listing out all the tasks and their descriptions.
     * @throws DukeException if task list is empty.
     */
    public String list(TaskList tasks) throws DukeException {
        ArrayList<Task> store = tasks.list;
        if (store.size() == 0) {
            throw new DukeException("☹ OOPS!!! Your list is still empty!");
        }
        String foundItems = "Here are the tasks in your list:" + "\n";
        for (int i = 0; i < store.size(); i++) {
            int taskNumber = i + 1;
            String taskString = Integer.toString(taskNumber);
            String taskStatus;
            if (i == store.size() - 1) {
                taskStatus = (taskString + " " + store.get(i).toString());
            } else {
                taskStatus = (taskString + " " + store.get(i).toString() + "\n");
            }
            foundItems = foundItems + taskStatus;
        }
        return foundItems;
    }

    public String bye() {
        return "Bye. Hope to see you again soon!!!";
    }

    /**
     * Returns a string that will be used as a response to "todo" or "deadline" or "event" command.
     *
     * @param toAdd The task to be added to Duke.
     * @param tasks Duke's current Task List.
     * @return Response string to add commands.
     */
    public String addTask(Task toAdd, TaskList tasks) {
        String deadlineInfo = toAdd.toString() + "\n";
        String openingText = "Got it. I've added this task: " + "\n";
        int tasksSize = tasks.count();
        String tasksCount = Integer.toString(tasksSize);
        String closingText = "Now you have " + tasksCount + " tasks in the list.";
        return openingText + deadlineInfo + closingText;
    }

    /**
     * Returns a string that will be used as a response to "delete" command.
     *
     * @param deleted Task to be deleted.
     * @param tasks Duke's current Task List.
     * @return Response string to delete command.
     */
    public String deleteTask(Task deleted, TaskList tasks) {
        String openingText = "Noted. I've removed this task: " + "\n";
        String deletedInfo = deleted.toString() + "\n";
        int tasksSize = tasks.count();
        String tasksCount = Integer.toString(tasksSize);
        String closingText = "Now you have " + tasksCount + " tasks in the list.";
        return openingText + deletedInfo + closingText;
    }

    /**
     * Returns a string that will be used as a response to "done" command.
     *
     * @param done Task to be marked as done.
     * @return Response string to done command.
     */
    public String doneTask(Task done) {
        String openingText = "Nice! I've marked this task as done: " + "\n";
        String doneInfo = done.toString();
        return openingText + doneInfo;
    }

    public String findEmpty() {
        return "No items found!";
    }

    /**
     * Returns a string that will be used as a response to "find" command.
     *
     * @param found ArrayList of tasks which match the search keyword.
     * @return Response string to find command listing all matching tasks.
     */
    public String foundItems(ArrayList<Task> found) {
        String foundText = "Here are the matching tasks in your list:" + "\n";
        for (int i = 0; i < found.size(); i++) {
            String itemInfo;
            if (i == found.size() - 1) {
                itemInfo = found.get(i).toString();
            } else {
                itemInfo = found.get(i).toString() + "\n";
            }
            foundText = foundText + itemInfo;
        }
        return foundText;
    }

    public String printException(Exception e) {
        return e.getMessage();
    }
}