package duke.component;

import duke.component.DukeException;
import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> taskList;

    public TaskList() { this.taskList = new ArrayList<>(); }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public void add(Task t) {
        this.taskList.add(t);
    }

    public Task deleteAt(int index) throws DukeException {
        if (index >= taskList.size() || index < 0) {
            throw new DukeException("Invalid task number!");
        }
        return this.taskList.remove(index);
    }

    public Task getAtIndex(int index) {
        return taskList.get(index);
    }

    public int getSize() {
        return taskList.size();
    }

    public void replace(int index, Task oldTask) {
        this.taskList.set(index, oldTask.changeToCompletedStatus());
    }

    @Override
    public String toString() {
        String result = "\t____________________________________________________________" +
                            "\n\t Here are the tasks in your list:";

        for (int i = 0; i < taskList.size(); i++) {
            result = result + "\n\t" + " " + (i + 1) + ". " + taskList.get(i);
        }

        result = result + "\n\t____________________________________________________________\n";

        return result;
    }
}
