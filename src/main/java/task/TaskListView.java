package task;

import util.DukeMessage;
import util.DukeOutput;

import java.util.List;

class TaskListView {
    public void displayAllTasks(List<Task> tasks) {
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

        DukeOutput.printMessage(taskList);
    }

    private DukeMessage formatTaskMessage(Task task) {
        return new DukeMessage(task.getTaskMessage());
    }

    public void displayNewTask(Task task, int tasksLength) {
        DukeMessage newTaskMessage = new DukeMessage("Got it. I've added this task:")
                .newLine()
                .indent()
                .append(task.getTaskMessage())
                .newLine()
                .append(String.format("Now you have %d tasks in the list", tasksLength));
        DukeOutput.printMessage(newTaskMessage);
    }

    public void displayTaskDone(Task task) {
        DukeMessage taskDoneMessage = new DukeMessage("Nice! I've marked this task as done:")
                .newLine()
                .indent()
                .append(task.getTaskMessage());
        DukeOutput.printMessage(taskDoneMessage);
    }
}
