package task;

import Exception.DukeException;

import java.util.ArrayList;

public class TaskList {

    ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns all the tasks.
     *
     * @return {@link ArrayList} all tasks in ArrayList form.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (tasks.size() > 0) {
            sb.append("Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                sb.append(i + 1 + ". " + tasks.get(i) + "\n");
            }
        } else {
            sb.append("Looks like you have no tasks!");
        }
        return sb.toString().trim();
    }

    /**
     * Sets the task to be done.
     *
     * @param i index of the task.
     */
    public void done(int i) {
        tasks.get(i).done();
    }

    /**
     * Returns the task with the specified index.
     *
     * @param i index of the task.
     * @return: Specified task.
     */
    public Task getTask(int i) {
        return tasks.get(i);
    }

    /**
     * Deletes the task with the specified index.
     *
     * @param i index of the task.
     * @throws DukeException when an error occurred with a specific message.
     * @return: Deleted task.
     */
    public Task deleteTask(int i) throws DukeException {
        try {
            return tasks.remove(i);
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException("that task does not exists");
        }
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return {@link int} Number of tasks in the task list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Finds a text.
     * @param description search string.
     * @return {@link String}
     */
    public String findTask(String description) {
        StringBuilder sb = new StringBuilder();
        ArrayList<Task> filteredTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(description)) {
                filteredTasks.add(tasks.get(i));
            }
        }
        if (filteredTasks.size() > 0) {
            sb.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < filteredTasks.size(); i++) {
                sb.append(i + 1 + ". " + filteredTasks.get(i) + "\n");
            }
        } else {
            sb.append("Looks like there is no task matching to \"" + description + "\"");
        }
        return sb.toString().trim();
    }
}
