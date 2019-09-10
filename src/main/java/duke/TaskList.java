package duke;

import duke.task.Task;

import java.util.LinkedList;

public class TaskList {

    private LinkedList<Task> taskList;

    public TaskList() {
        this.taskList = new LinkedList<Task>();
    }

    public TaskList(LinkedList<Task> taskList) {
        this.taskList = taskList;
    }

    public LinkedList<Task> getTasks() {
        return taskList;
    }

    public Task markDone(int taskNum) throws DukeException {
        if (taskNum + 1 > taskList.size() || taskNum + 1 < 0) {
            throw new DukeException("\u2639 OOPS!!! This duke.task does not exist.");
        }
        Task t = taskList.remove(taskNum);
        t.markAsDone();
        taskList.add(taskNum, t);
        return t;
    }

    public Task deleteTask(int taskNum) throws DukeException {
        if (taskNum + 1 > taskList.size() || taskNum + 1 < 0) {
            throw new DukeException("\u2639 OOPS!!! This duke.task does not exist.");
        }
        Task t = taskList.remove(taskNum);
        return t;
    }

    public void addTask(Task t) {
        taskList.add(t);
    }

    public LinkedList<Task> findTasks(String toFind) {
        LinkedList<Task> result = new LinkedList<>();
        CharSequence cs = toFind;

        for (int k = 0; k < taskList.size(); k++) {
            Task t = taskList.get(k);
            if (t.getDescription().contains(cs)) {
                result.add(t);
            } else {
                result.add(new Task("fake task"));
            }
        }

        return result;
    }

}
