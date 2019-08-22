package task;

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
    }

    public void displayTasks() {
        view.displayAllTasks(tasks);
    }
}
