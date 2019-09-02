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

    public void showListOfTask(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            Task evaluatingTask = tasks.get(i - 1);
            System.out.println(i + "." + evaluatingTask.toString());
        }
        System.out.println();
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

    public void sayYourGoodBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void doneAnnouncement(Task task) {
        System.out.println("Nice! I've marked this task as done: " + "\n"
                + "    " + task + "\n");
    }

    public void deleteAnnouncement(Task task, int taskCount) {
        System.out.println("Noted. I've removed this task:" + "\n"
                + "    " + task);
        System.out.println(taskCounter(taskCount) + "\n");
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

    public void announceMatchingTask(ArrayList<Task> taskList) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 1; i <= taskList.size(); i++) {
            Task evaluatingTask = taskList.get(i - 1);
            System.out.println(i + "." + evaluatingTask.toString());
        }
        System.out.println();
    }
}
