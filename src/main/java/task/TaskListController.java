package task;

import util.DukeMessage;
import util.DukeOutput;

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

    public void setTaskToDone(int index) {
        tasks.get(index).setDone(true);
    }

    public DukeMessage getAllTasksMessage() {
        return view.formatAllTasksMessage(tasks);
    }

    public DukeMessage getTaskMessage(int index) {
        return view.formatTaskMessage(tasks.get(index));
    }
}
