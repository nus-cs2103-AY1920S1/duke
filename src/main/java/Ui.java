import Task.Task;

import java.util.ArrayList;

public class Ui {

    public String greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return "Hello from\n" + logo + "\n What can I do for you? \n";
    }

    public String taskDone(Task task) {
        return "Nice! I've marked this task as done: \n " +
                task.toString() + "\n";
    }

    public String taskDeleted(Task task) {
        return "Noted. I've removed this task:  \n " +
                task.toString();
    }

    public String showNumberOfTasks(ArrayList<Task> list) {
        return "Now you have " + list.size() + " task(s) in the list.";
    }

    public String taskCreated(Task task) {
        return "Got it. I've added this task: \n"
                + task.toString();
    }

    public String sayGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    public String list(ArrayList<Task> list) {
        String result = "Here are the tasks in your list: \n";
        for (int i = 0; i < list.size(); i++) {
            Task currTask = list.get(i);
//                    System.out.println(currTask.getClass());
            result += (i+1) + ". " + currTask.toString() + "\n";
        }
        return result;
    }

    public String showDescriptionEmptyError() {
        return "☹ OOPS!!! The description of a task cannot be empty.";
    }

    public String showWrongCommandError() {
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }

    public String showLoadingError() {
        return "☹ OOPS!!! Error loading file.";
    }

    public String noSuchTaskError() {
        return "Unable to find task. Please try again.";
    }
}
