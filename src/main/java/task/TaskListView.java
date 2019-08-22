package task;

import util.DukeMessage;

import java.util.List;

class TaskListView {
    public DukeMessage formatAllTasksMessage(List<Task> tasks) {
        DukeMessage taskList = new DukeMessage("Here are the task.tasks in your list:")
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
        return new DukeMessage(task.getTaskMessage());
    }

    public DukeMessage formatNewTask(Task task, int tasksLength) {
        return new DukeMessage("Got it. I've added this task:")
                .newLine()
                .indent()
                .append(task.getTaskMessage())
                .newLine()
                .append(String.format("Now you have %d tasks in the list", tasksLength));
    }

    public DukeMessage formatTaskDone(Task task) {
        return new DukeMessage("Nice! I've marked this task as done:")
                .newLine()
                .indent()
                .append(task.getTaskMessage());

    }
}
