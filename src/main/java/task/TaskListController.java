package task;

import util.DukeMessage;

import java.util.ArrayList;
import java.util.List;

public class TaskListController {
    private List<Task> tasks;
    private TaskListView view;

    public TaskListController() {
        tasks = new ArrayList<>();
        view = new TaskListView();
    }

    public void addTask(Task task) {
        tasks.add(task);
        view.displayNewTask(task, tasks.size());
    }

    public void setTaskToDone(int index) {
        tasks.get(index).setDone(true);
        view.displayTaskDone(tasks.get(index));
    }

    public void displayAllTasks() {
        view.displayAllTasks(tasks);
    }
}
