import java.util.ArrayList;

/**
 * Encapsulates the User Interface operations. It is responsible of all interaction with the user.
 */
public class Ui {

    public Ui() {

    }

    public String welcome() {
        String logo = ("Hello I am\n") +
                " _____                    \n"
                + "|__ __|_____ ____ _    __ _ ___\n"
                + "  | | |  _  |  __| \\  / /| |  _|\n"
                + " _| | | |_| | |   \\ \\/ / | |\\ \\ \n"
                + "|___|  \\__,_|_|    \\__/  |_|___|\n"
        + ("What can I do for you?");
        return logo;
    }

    public String showListOfTask(TaskList tasks) {
        if (tasks.size() == 0) {
            return "There's currently no task in your list.";
        }
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
                    + task + "\nNow you have " + taskCount
                    + " tasks in the list.\n");
        } else {
            return ("Got it. I've added this task:\n" + "    "
                    + task + "\nNow you have " + taskCount
                    + " task in the list.\n");
        }
    }

    public String sayYourGoodBye() {
        return ("Bye. Hope to see you again soon!");
    }

    public String doneAnnouncement(Task task) {
        return ("Nice! I've marked this task as done:\n"
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

    public String announceNoneMatchingTask() {
        return "Sorry, there are no matching tasks found.";
    }

    public String announceExisted() {
        return "Sorry, you can't add this task as the task already.";
    }
}
