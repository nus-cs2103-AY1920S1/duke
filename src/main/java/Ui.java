import java.util.ArrayList;

public class Ui {
    private String output;

    String getOutput() {
        return this.output;
    }

    String greet() { //duke greet
        return "Hello! I'm Duke.\n" + "What can I do for you?\n";
    }

    void setToFarewell() {
        output = "Bye. Hope to see you again soon!";
    }

    void setToList(TaskList taskList) {

        output = "Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.getTaskList().size(); i++) {
            String num = "" + (i + 1);
            output += "     " + num + ".[" + taskList.getTaskList().get(i).getStatusIcon() + "] "
                    + taskList.getTaskList().get(i).toString() + "\n";
        }
    }

    void setToTodo(Task todoTask, TaskList taskList) {
        output = "Got it. I've added this task:\n"
                + todoTask.toString() + "\n" +
                "Now you have " + taskList.getTaskList().size() + " tasks in the list.";
    }

    void setToDeadline(Task dlTask, TaskList taskList) {
        output = "Got it. I've added this task:\n"
                + dlTask.toString() + "\n" +
                "Now you have " + taskList.getTaskList().size() + " tasks in the list.";
    }

    void setToEvent(Task eventTask, TaskList taskList) {
        output = "Got it. I've added this task:\n"
                + eventTask.toString() + "\n" + "Now you have "
                + taskList.getTaskList().size() + " tasks in the list.";
    }

    void setToDone(int taskNum, TaskList taskList) {
        assert(taskNum > 0) : "Task number must be more than 1"; //added assert
        output = "Nice! I've marked this task as done:\n"
                + "[" + taskList.getTaskList().get(taskNum - 1).getStatusIcon() + "]"
                + taskList.getTaskList().get(taskNum - 1).toString();
    }

    void setToDelete(int taskNum2, TaskList taskList) {
        assert(taskNum2 > 0) : "Task number must be more than 1"; //added assert
        output = "Noted. I've removed this task:\n"
                + taskList.getTaskList().get(taskNum2 - 1).toString() + "\n"
                + "Now you have " + (taskList.getTaskList().size() - 1) + "tasks in the list.";
    }

    void setToFind(ArrayList<Task> relatedTasks, TaskList taskList, String findWord) {
        for (Task task : taskList.getTaskList()) {
            if (task.getDescription().contains(findWord)) {
                relatedTasks.add(task);
            }
        }
        output = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < relatedTasks.size(); i++) {
            int num = i + 1;
            output += num + "." + relatedTasks.get(i);
        }
    }

}
