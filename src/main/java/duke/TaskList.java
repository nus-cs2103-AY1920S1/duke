package duke;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class represents a TaskList which serves as a collection
 * of Task objects for duke.
 */
public class TaskList implements Serializable {
    private ArrayList<Task> taskList;

    /**
     * Returns an instance of TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Adds a Task to the TaskList.
     * @param task task to be inserted
     */
    public void add(Task task) {
        this.taskList.add(task);
    }

    /**
     * Removes a Task from the TaskList.
     * @param index task to be removed
     */
    public void remove(int index) {
        this.taskList.remove(index);
    }

    /**
     * Returns the Task at the specified index in the TaskList.
     * @param index index of the Task to return
     * @return the Task object the specified index in the TaskList
     */
    public Task get(int index) {
        return this.taskList.get(index);
    }

    /**
     * Returns an integer that indicates the amount of Tasks in the task list.
     * @return the amount of Tasks in the list
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Return a String representation of all Tasks in the task list.
     * @return a string representation of all Task objects in the task list.
     * @throws DukeException if the list is empty
     */
    public String getList() throws DukeException {
        int count = 1;
        StringBuilder output = new StringBuilder();

        output.append("Here are the tasks in your list:\n");

        // List and print all items stored
        for (Task item: this.taskList) {
            //noinspection StringConcatenationInsideStringBufferAppend
            output.append(count++ + "." + item + '\n');
        }

        // Remove terminal newline character if at least 1 item inserted
        if (count > 1) {
            output.deleteCharAt(output.toString().length() - 1);
            return output.toString();
        } else {
            throw new DukeException("The task list is empty.");
        }
    }

    /**
     * Returns the string representation of Tasks that matches a partial search term.
     * @param regex a string representing the search term
     * @return a string containing the string representation of all Task
     *     objects that match the search term.
     * @throws DukeException if the list is empty
     */
    public String getSearchList(String regex) throws DukeException {
        String listOutput = this.getList();
        List<String> tasks =
                new ArrayList<>(Arrays.asList(listOutput.split("\n")));

        tasks.removeIf(x -> !x.contains(regex));

        return "Here are the matching tasks in your list:\n"
                + String.join("\n", tasks);
    }
}
