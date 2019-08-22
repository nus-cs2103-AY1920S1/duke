package task;

import util.DukeMessage;
import util.DukeOutput;

import java.util.List;

class TaskListView {
    public DukeMessage formatAllTasksMessage(List<Task> tasks) {
        DukeMessage taskList = new DukeMessage("Here are the tasks in your list:")
                .newLine();

        if (!tasks.isEmpty()) {
            taskList.append("1.")
                    .append(formatTaskMessage(tasks.get(0)));
        }

        for (int i = 1; i < tasks.size(); i++) {
            taskList.newLine()
                    .append(i + 1)
                    .append(".")
                    .append(formatTaskMessage(tasks.get(i)));
        }

        return taskList;
    }

    public DukeMessage formatTaskMessage(Task task) {
        DukeMessage taskMessage = new DukeMessage();
        if (task.isDone()) {
            taskMessage.append("[✓] ");
        } else {
            taskMessage.append("[✗] ");
        }

        taskMessage.append(task.getDescription());

        return taskMessage;
    }
}
