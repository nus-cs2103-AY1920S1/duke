package duke;

import duke.task.Task;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The class that contains the task list.
 */
public class TaskList {
    protected List<Task> tasks;
    protected Storage storage;

    /**
     * Public constructor for Tasklist class.
     *
     * @param storage Storage object containing all the methods for writing and retrieving from the data file.
     */
    public TaskList(Storage storage) {
        this.storage = storage;
        this.tasks = storage.load();
    }

    /**
     * Updates the tasks list in the data file.
     */
    private void update() {
        try {
            storage.update(tasks);

        } catch (Exception e) {
            assert false;
        }
    }

    /**
     * Gets the number of tasks in the task list.
     * @return The number of tasks in the task list.
     */
    public int getNoOfTasks() {
        return tasks.size();
    }

    /**
     * Iterates through all the tasks in the task list.
     * Creates a String containing information about all the tasks.
     *
     * @return String containing information about all the tasks.
     */
    public String view() {
        if (tasks.size() > 0) {
            StringBuilder str = new StringBuilder();
            str.append("Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                str.append(String.format("%d. %s\n", (i + 1), tasks.get(i)));
            }
            str.deleteCharAt(str.length() - 1);
            return str.toString();

        } else {
            return "You have no tasks in your list! Add one now! ;~)";
        }
    }

    /**
     * Adds a Task to the task list.
     *
     * @param t The Task object to be added.
     * @return The chatbot's response in String format.
     */
    public String add(Task t) {
        tasks.add(t);
        update();
        return String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks.",
                tasks.get(tasks.size() - 1), tasks.size());
    }

    /**
     * Deletes a Task from the task list.
     *
     * @param indexes The index of the Task object to be deleted.
     * @return The chatbot's response in String format.
     */
    public String delete(int[] indexes) {
        StringBuilder output = new StringBuilder("Noted. I've removed this task:\n");

        Arrays.sort(indexes);
        if ((indexes[0] <= 0) || (indexes[indexes.length - 1] > tasks.size())) {
            return ">:( !!! Please specify an index within the range.";
        }

        int reduce = 0;
        for (int index : indexes) {
            output.append(String.format("\t%s\n", tasks.get(index - 1 - reduce)));
            tasks.remove(index - 1 - reduce);
            reduce += 1;
        }

        update();
        output.append(String.format("Now you have %d tasks.", tasks.size()));
        output.deleteCharAt(output.length() - 1);
        return output.toString();
    }

    /**
     * Finds a matching task given the String input.
     *
     * @param input The matching String input.
     * @return The chatbot's response in String format.
     */
    public String find(String input) {
        StringBuilder str = new StringBuilder();
        str.append("Here are the matching tasks:\n");

        int num = 1;
        for (Task t : tasks) {
            if (t.getDescription().contains(input)) {
                str.append(String.format("%d. %s\n", num, t));
                num++;
            }
        }
        str.deleteCharAt(str.length() - 1);
        return str.toString();
    }

    /**
     * Marks a Task in a task list as done.
     *
     * @param indexes The indexes of the Task object to be modified.
     * @return The chatbot's response in String format.
     */
    public String markDone(int[] indexes) {
        StringBuilder output = new StringBuilder("Nice! I've marked this task as done:\n");

        Arrays.sort(indexes);
        if ((indexes[0] <= 0) || (indexes[indexes.length - 1] > tasks.size())) {
            return ">:( !!! Please specify an index within the range.";
        }

        for (int index : indexes) {
            tasks.get(index - 1).markAsDone();
            output.append(String.format("\t%s\n", tasks.get(index - 1)));
        }

        update();
        output.deleteCharAt(output.length() - 1);
        return output.toString();
    }
}
