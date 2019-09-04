import java.util.ArrayList;

/**
 * Encapsulates the User Interface operations. It is responsible of all interaction with the user.
 */

public class Ui {

    public Ui() {

    }

    public void welcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I am\n" + logo);
        System.out.println("What can I do for you?");
    }

    public String showListOfTask(TaskList tasks) {
        String string = "Here are the tasks in your list:";
        for (int i = 1; i <= tasks.size(); i++) {
            Task evaluatingTask = tasks.get(i - 1);
            string += "\n" + i + "." + evaluatingTask.toString();
        }
        return string;
    }

    public String newTaskAdded(Task task, int taskCount) {
        if (taskCount > 1) {
            return ("Got it. I've added this task:\n" + "    "
                    + task + "\n" + "Now you have " + taskCount
                    + " tasks in the list.\n");
        } else {
            return ("Got it. I've added this task:\n" + "    "
                    + task + "\n" + "Now you have " + taskCount
                    + " task in the list.\n");
        }
    }

    public String sayYourGoodBye() {
        return ("Bye. Hope to see you again soon!");
    }

    public String doneAnnouncement(Task task) {
        return ("Nice! I've marked this task as done: " + "\n"
                + "    " + task + "\n");
    }

    public String deleteAnnouncement(Task task, int taskCount) {
        return ("Noted. I've removed this task:" + "\n"
                + "    " + task) + ("\n" + taskCounter(taskCount) + "\n");
    }

    private String taskCounter(int taskCount) {
        if (taskCount > 1) {
            return ("Now you have " + taskCount
                    + " tasks in the list." + "\n");
        } else {
            return ("Now you have " + taskCount
                    + " task in the list." + "\n");
        }
    }

    public String announceMatchingTask(ArrayList<Task> taskList) {
        String header = "Here are the matching tasks in your list:";
        for (int i = 1; i <= taskList.size(); i++) {
            Task evaluatingTask = taskList.get(i - 1);
            return header + ("\n" + i + "." + evaluatingTask.toString());
        }
        return new String();
    }
}
