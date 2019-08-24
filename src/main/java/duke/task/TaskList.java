package duke.task;

import duke.exception.DukeException;
import duke.exception.InvalidInputDukeException;
import duke.exception.InvalidTaskDukeException;
import java.util.ArrayList;
import java.util.List;

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

    public Task deleteTask(int index) throws DukeException {
        if (index < 0 || index >= taskList.size()) {
            throw new InvalidTaskDukeException("Task does not exist");
        }

        return taskList.remove(index);
    }

    public Task getTask(int index) throws DukeException {
        if (index < 0 || index >= taskList.size()) {
            throw new InvalidTaskDukeException("Task does not exist");
        }

        return taskList.get(index);
    }

    public int size() {
        return taskList.size();
    }

    public List<Task> getTaskList() {
        return taskList;
    }
}
