package duke;

import duke.task.Task;
import duke.ui.Ui;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        assert tasks != null;
        this.list = tasks;
    }

    public void add(Task task) {
        assert task != null;
        list.add(task);
    }

    public Task delete(int taskNum) {
        Task task = list.remove(taskNum - 1);
        return task;
    }

    public int size() {
        return list.size();
    }

    public ArrayList<Task> getTaskArrayList() {
        return this.list;
    }

    public Task setDone(int taskNum) {
        Task task = list.get(taskNum - 1);
        task.setAsDone();
        return task;
    }

    public ArrayList<Task> findMatch(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : list) {
            if (task.getDesc().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
