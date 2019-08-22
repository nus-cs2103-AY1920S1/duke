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

    public DukeMessage addTask(Task task) {
        tasks.add(task);
        return view.formatNewTask(task, tasks.size());
    }

    public DukeMessage setTaskToDone(int index) {
        tasks.get(index).setDone(true);
        return view.formatTaskDone(tasks.get(index));
    }

    public DukeMessage getAllTasksMessage() {
        return view.formatAllTasksMessage(tasks);
    }
}
