package duke.task;

import duke.exception.InvalidTaskDukeException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Manages a list of tasks.
 */
public class TaskList {
    private List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes a task from the list and returns it.
     *
     * @param index Index of the task.
     * @return The task after being removed from the list.
     * @throws InvalidTaskDukeException If the task does not exist.
     */
    public Task deleteTask(int index) throws InvalidTaskDukeException {
        if (index < 0 || index >= taskList.size()) {
            throw new InvalidTaskDukeException("Task does not exist");
        }

        return taskList.remove(index);
    }

    /**
     * Gets a task.
     *
     * @param index Index of the task.
     * @return The task based on index.
     * @throws InvalidTaskDukeException If the task does not exist.
     */
    public Task getTask(int index) throws InvalidTaskDukeException {
        if (index < 0 || index >= taskList.size()) {
            throw new InvalidTaskDukeException("Task does not exist");
        }

        return taskList.get(index);
    }

    /**
     * Finds tasks based on given keywords.
     * The entire task description is checked if it contains the keyword,
     * and is then added to a new list.
     *
     * @param keyword For matching tasks.
     * @return List of tasks based on the given keywords.
     */
    public List<Task> findTasksByKeyword(String keyword) {
        return taskList.stream()
                .filter(task -> task.getDescription().contains(keyword))
                .collect(Collectors.toList());
    }

    public int size() {
        return taskList.size();
    }

    public List<Task> getTaskList() {
        return taskList;
    }
}
