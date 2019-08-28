package task;

import java.util.List;
import java.util.stream.Collectors;

public class TaskListController {
    private List<Task> tasks;
    private TaskListView view;

    public TaskListController(List<Task> tasks) {
        this.tasks = tasks;
        view = new TaskListView();
    }

    public List<Task> getTasks() {
        return tasks;
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

    public void deleteTask(int index) {
        Task deletedTask = tasks.get(index);
        tasks.remove(index);
        view.displayTaskDeleted(deletedTask, tasks.size());

    }

    public void findTasks(String parameter) {
        List<Task> matchingTasks = tasks.stream()
                .filter(task -> task.getDescription().contains(parameter))
                .collect(Collectors.toList());
        view.displayMatchingTasks(matchingTasks);
    }
}
