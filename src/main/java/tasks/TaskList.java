package tasks;

import exceptions.DukeException;
import exceptions.InvalidDescriptionException;
import exceptions.TaskNotFoundException;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns the total number of lists.
     * @return size of list as an integer
     */
    public int getListSize() {
        return tasks.size();
    }

    public String getTaskString(int index) {
        return tasks.get(index).toString();
    }

    /**
     * Finds the task of which description contains the key string.
     * @param key keyword inputted by user
     * @return list of tasks that contains the keyword
     */
    public ArrayList<Task> findTasks (String key) throws TaskNotFoundException {
        ArrayList<Task> temp = new ArrayList<>();
        for (Task t: tasks) {
            if (t.getDescription().contains(key)) {
                temp.add(t);
            }
        }
        if (temp.isEmpty()) {
            throw new TaskNotFoundException("There are no tasks with the inputted key :(");
        }
        return temp;
    }

    /**
     * Adds a new task to the list of tasks
     * @param task task to be added
     * @throws DukeException
     */
    public void addNewTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the list of tasks and returns it.
     * @param index index of file to be deleted
     * @return deleted task
     */
    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Marks the indicated task as done.
     * @param taskIndex task to be marked as done
     */
    public void markAsDone(int taskIndex) throws DukeException {
        boolean markedAsDone = tasks.get(taskIndex).markAsDone();
        assert markedAsDone: "TaskList.markAsDone() error";
    }

    /**
     * Returns the list of tasks as an array list.
     * @return
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Edits the description of the indicated task.
     */
    public void editTaskDescription(int taskIndex, String newDescription) throws InvalidDescriptionException {
        if (newDescription.isEmpty()) {
            throw new InvalidDescriptionException("Updated description cannot be empty! :)");
        } else {
            tasks.get(taskIndex).setDescription(newDescription);
        }
    }
}
