package duke.sheet;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Encapsulates a sheet for the list of tasks.
 * Includes operations to add/delete tasks in the list.
 */
public class Sheet {
    private List<Task> tasks;
    private int numOfTask;

    /**
     * Construct a task list.
     *
     * @param tasks List of tasks contained the list.
     */
    public Sheet(List<Task> tasks) {
        this.tasks = tasks;
        numOfTask = tasks.size();
    }

    public List<Task> getList() {
        return this.tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to be added to the list.
     */
    public void add(Task task) {
        tasks.add(task);
        numOfTask++;
    }

    /**
     * Deletes a task from the list.
     *
     * @param index Serial index of the task in list.
     */
    public Task delete(int index) {
        Task removed = tasks.remove(index - 1);
        numOfTask--;
        return removed;
    }

    /**
     * Checks whether the task list is empty.
     *
     * @return True if the task list is empty.
     */
    public boolean isEmpty() {
        return this.tasks.size() == 0;
    }

    /**
     * Marks the task as done.
     *
     * @param index Serial index of the task in list.
     */
    public Task markAsDone(int index) {
        Task doneTask = tasks.get(index - 1).finish();
        tasks.set(index - 1, doneTask);
        return doneTask;
    }

    /**
     * Outputs the number of tasks in the list.
     *
     * @return int Number of tasks in the list.
     */
    public int getNumOfTask() {
        return numOfTask;
    }

    /**
     * Outputs the task list.
     *
     * @return String representation of the list.
     */
    public String showList() {
        StringBuilder sb = new StringBuilder("");

        for (int i = 0; i < numOfTask; i++) {
            sb.append(" ").append(i + 1).append(". ").append(tasks.get(i).toString().trim()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Sorts the tasks in the list and outputs the required number of tasks.
     *
     * @param requiredNum Required number of tasks.
     * @return String representation of the sorted first few tasks.
     */
    public String sortList(int requiredNum) {
        int numOfReminder = Math.min(requiredNum, numOfTask);
        List<Task> sortedList = new ArrayList<>(tasks);
        Collections.sort(sortedList);

        StringBuilder sb = new StringBuilder("");

        int i = 0;
        int j = 0;
        while (i < numOfReminder && j < numOfTask) {
            if (sortedList.get(j).isDone()) {
                j++;
                continue;
            }
            sb.append(" ").append(i + 1).append(". ").append(sortedList.get(j).toString().trim()).append("\n");
            j++;
            i++;
        }

        if (sb.toString().isBlank()) {
            sb.append("There are currently no urgent tasks~");
        } else {
            sb.append("Don't miss them! > <");
        }
        return sb.toString();
    }

    /**
     * Remove all tasks from the task list.
     */
    public void clearList() {
        tasks.clear();
        numOfTask = 0;
    }

    /**
     * Searches for tasks containing the keyword.
     *
     * @param keyword Keyword for searching.
     */
    public String find(String keyword) {
        StringBuilder sb = new StringBuilder("");
        int count = 0;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                count++;
                sb.append(" ").append(count).append(". ").append(task.toString().trim()).append("\n");
            }
        }
        if (count == 0) {
            sb.append("> < Sorry, nothing has been found.\n");
        }
        return sb.toString();
    }

    /**
     * ToString method of the sheet object.
     *
     * @return String representation of the sheet of task list.
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < numOfTask; i++) {
            output.append(i + 1).append(". ").append(tasks.get(i).toString());
        }
        return output.toString().trim();
    }

}
