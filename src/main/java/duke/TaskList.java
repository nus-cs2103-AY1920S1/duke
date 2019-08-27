package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    /**
     * Constructs an empty list of tasks.
     */
    public TaskList() {
        list = new ArrayList<>();
    }

    /**
     * Constructs a list of tasks using imported raw string from the file system.
     *
     * @param s the raw string loaded from the file system.
     * @throws DukeException if the raw string cannot be formatted into tasks.
     */
    public TaskList(String s) throws DukeException {
        String[] lines = s.split("\n");
        list = new ArrayList<>();
        for (String line : lines) {
            addTask(Task.fromImportFormat(line));
        }
    }

    /**
     * Gets the number of tasks in the list.
     *
     * @return the number of tasks in the list.
     */
    public int getSize() {
        return list.size();
    }

    /**
     * Gets the list of tasks.
     *
     * @return the list of tasks.
     */
    public ArrayList<Task> getTaskList() {
        return list;
    }

    /**
     * Gets a particular task from the list.
     *
     * @param taskNum the index number of the task to be retrieved.
     * @return the task retrieved from the list.
     * @throws DukeException if the task cannot be found.
     */
    public Task getTask(int taskNum) throws DukeException {
        try {
            return list.get(taskNum - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Index is out of bounds.");
        }
    }

    /**
     * Adds a task to the list.
     *
     * @param task the task to be added to the list.
     */
    public void addTask(Task task) {
        list.add(task);
    }

    /**
     * Deletes a particular task from the list.
     *
     * @param taskNum the index number of the task to be deleted.
     * @throws DukeException if the task cannot be found.
     */
    public void deleteTask(int taskNum) throws DukeException {
        try {
            list.remove(taskNum - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Index is out of bounds.");
        }
    }

    @Override
    public String toString() {
        StringBuilder myBuilder = new StringBuilder();
        for (int i = 1; i <= list.size(); i++) {
            Task myTask = list.get(i - 1);
            myBuilder.append(i + "." + myTask);
            if (i < list.size()) {
                myBuilder.append("\n");
            }
        }
        return myBuilder.toString();
    }
}
