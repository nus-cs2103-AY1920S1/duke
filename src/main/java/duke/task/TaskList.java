package duke.task;

import duke.exception.DukeException;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    /**
     * The list of tasks is stored as an ArrayList{@literal <Task>}.
     */
    private ArrayList<Task> taskArrayList;

    /**
     * Constructor for TaskList which accepts an existing {@literal ArrayList<Task>}.
     *
     * @param taskArrayList The ArrayList of tasks
     */
    public TaskList(ArrayList<Task> taskArrayList) {
        this.taskArrayList = taskArrayList;
    }

    /**
     * Constructor for TaskList which creates an empty ArrayList.
     */
    public TaskList() {
        this.taskArrayList = new ArrayList<>();
    }

    /**
     * Returns the size of the list of tasks of this TaskList.
     *
     * @return size of the ArrayList inside the TaskList
     */
    public int getSize() {
        return this.taskArrayList.size();
    }

    /**
     * Adds a Task to the TaskList.
     *
     * @param taskToAdd The Task to be added.
     */
    public void addTaskToList(Task taskToAdd) {
        this.taskArrayList.add(taskToAdd);
    }

    /**
     * Returns the Task from the TaskList at the one-indexed position provided in the argument.
     *
     * @param taskNumToGet one-indexed position of the task
     * @return the Task at the one-indexed position
     * @throws DukeException Throws an exception if the task number is out bounds
     */
    public Task getTaskFromList(int taskNumToGet) throws DukeException {
        if (taskNumToGet <= 0 || taskNumToGet > this.getSize()) {
            throw new DukeException("Task Number is out of bounds");
        }
        return this.taskArrayList.get(taskNumToGet - 1);
    }

    /**
     * Deletes the Task from the TaskList at the one-indexed position provided in the argument.
     *
     * @param taskNumToDelete one-indexed position of the task to delete
     * @return the Task at the one-indexed position
     * @throws DukeException Throws an exception if the task number is out bounds
     */
    public Task deleteTaskFromList(int taskNumToDelete) throws DukeException {
        if (taskNumToDelete <= 0 || taskNumToDelete > this.getSize()) {
            throw new DukeException("Task Number is out of bounds");
        }
        return this.taskArrayList.remove(taskNumToDelete - 1);
    }

    /**
     * Marks the Task in the TaskList at the one-indexed position provided in the argument as done.
     *
     * @param taskNumToMark one-indexed position of the task
     * @throws DukeException Throws an exception if the task number is out bounds
     */
    public void markTaskAsDone(int taskNumToMark) throws DukeException {
        if (taskNumToMark <= 0 || taskNumToMark > this.getSize()) {
            throw new DukeException("Task Number is out of bounds");
        }
        Task taskToMark = this.getTaskFromList(taskNumToMark);
        Task markedTask = taskToMark.markAsDone();
        System.out.println("SETTING");
        setIndexToTask(taskNumToMark, markedTask);
    }

    /**
     * Sets the Task from the TaskList at the one-indexed position provided in the argument.
     * The position should already exist in the TaskList as this is used to update a task.
     *
     * @param numberToSet one-indexed position of the task
     * @param taskToSet the Task to be set at that position
     * @throws DukeException Throws an exception if the task number is out bounds
     */
    public void setIndexToTask(int numberToSet, Task taskToSet) throws DukeException {
        if (numberToSet <= 0 || numberToSet > this.getSize()) {
            throw new DukeException("Task Number is out of bounds");
        }
        this.taskArrayList.set(numberToSet - 1, taskToSet);
    }

    /**
     * Returns a list of all the tasks as a string.
     * Currently used for storage only.
     *
     * @return String of all the Tasks in the format for storage.
     */
    public String listAllTasksAsString() {
        StringBuilder allTasks = new StringBuilder();
        for (Task task : this.taskArrayList) {
            allTasks.append(task.getStoredTaskStatus());
            allTasks.append(System.lineSeparator());
        }
        return allTasks.toString();
    }

    /**
     * Returns the ArrayList{@literal <Task>} encapsulated within the TaskList.
     *
     * @return the ArrayList of this TaskList.
     */
    public ArrayList<Task> getTaskArrayList() {
        return this.taskArrayList;
    }


}
