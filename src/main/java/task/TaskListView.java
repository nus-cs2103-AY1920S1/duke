package task;

import util.DukeOutput;

import java.util.List;

class TaskListView {
    void displayAllTasks(List<String> tasks) {
        StringBuilder message = new StringBuilder();

        if (!tasks.isEmpty()) {
            message.append("1. ").append(tasks.get(0));

            for (int i = 1; i < tasks.size(); i++) {
                message.append("\n")
                        .append(i + 1)
                        .append(". ")
                        .append(tasks.get(i));
            }
        }

        DukeOutput.printMessage(message.toString());
    }
}
