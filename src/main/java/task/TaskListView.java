package task;

import util.DukeOutput;

import java.util.List;

class TaskListView {
    void displayAllTasks(List<Task> tasks) {
        StringBuilder message = new StringBuilder();

        for (int i = 0; i < tasks.size(); i++) {
            message.append(i + 1)
                    .append(". ")
                    .append(formatTaskMessage(tasks.get(i)))
                    .append("\n");
        }

        DukeOutput.printMessage(message.toString());
    }

    String formatTaskMessage(Task task) {
        StringBuilder message = new StringBuilder();
        if (task.isDone()) {
            message.append("[✓] ");
        } else {
            message.append("[✗] ");
        }

        message.append(task.getDescription());

        return message.toString();
    }
}
