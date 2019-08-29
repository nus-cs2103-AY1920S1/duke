/**
 * Contains the TaskList and has the operations to add/delete tasks in the list
 */

import java.util.List;

public class TaskList {
    private List<Task> taskList;

    public TaskList (List<Task> taskList) {
        this.taskList = taskList;
    }

    public List<Task> getTaskList () {
        return taskList;
    }

    private int parseTaskInt(String str) throws DukeException {
        int taskInt;
        try {
            taskInt = Integer.parseInt(str);
            if (taskInt > taskList.size() || taskInt < 0) {
                throw new DukeException("That task number does not exist, please try again");
            }
        } catch (NumberFormatException e) {
            throw new DukeException("Please state a valid task number");
        }

        return taskInt;
    }

    public void addTask(Task task) {
        taskList.add(task);
        StringBuilder sb = new StringBuilder();
        sb.append("Got it. I've added this task:\n");
        sb.append("  " + task + "\n");
        sb.append("Now you have " + taskList.size() + " tasks in the list.");
        Ui.printStr(sb.toString());
    }

    public void markTask(String taskNumStr) throws DukeException { //mark as done. Need to check if already done, and if so throw exception
        int taskNum = parseTaskInt(taskNumStr);
        StringBuilder sb = new StringBuilder();
        Task task = taskList.get(taskNum - 1);
        task.markDone();
        sb.append("Nice! I've marked this task as done:\n");
        sb.append("  " + task.toString());
        Ui.printStr(sb.toString());
    }

    public void deleteTask(String taskNumStr) throws DukeException {
        int taskNum = parseTaskInt(taskNumStr);
        StringBuilder sb = new StringBuilder();
        Task t = taskList.get(taskNum - 1);
        sb.append("Noted. I've removed this task: \n");
        sb.append("  " + t + "\n");
        taskList.remove(taskNum - 1);
        sb.append("Now you have " + taskList.size() + " tasks in the list.");
        Ui.printStr(sb.toString());
    }

}
