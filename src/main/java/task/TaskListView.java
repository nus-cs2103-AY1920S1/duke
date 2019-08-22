package task;

import util.DukeOutput;

import java.util.List;

class TaskListView {
    void displayAllTasks(List<Task> tasks) {
        StringBuilder message = new StringBuilder();

        if (!tasks.isEmpty()) {
            message.append("1. ")
                    .append(formatTaskMessage(tasks.get(0)));
        }

        for (int i = 1; i < tasks.size(); i++) {
            message.append("\n")
                    .append(i + 1)
                    .append(". ")
                    .append(formatTaskMessage(tasks.get(i)));
        }

        DukeOutput.printMessage(message.toString());
    }

    private String formatTaskMessage(Task task) {
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
