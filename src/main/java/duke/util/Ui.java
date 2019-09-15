package duke.util;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Creates a UI class to manage UI for users.
 */
public class Ui {
    private static final String LINES = "____________________________________________________________\n";

    public Ui() {
    }

    /**
     * Prints loading Error.
     */
    public void showLoadingError() {
        System.out.println("Cannot load previous task");
    }

    /**
     * Prints the UI for ending the programme.
     *
     * @param tasks TaskList object.
     * @return Exit message.
     */
    public String showExitMessage(ArrayList<Task> tasks) {
        String message = "Saving your current tasks : \n";
        for (Task t : tasks) {
            message += t.toString() + "\n";
        }
        message += "Bye. Hope to see you again soon!";
        return message;
    }

    /**
     * Prints a welcome message for users.
     *
     * @param tasks TaskList object.
     * @return Welcome message.
     */
    public String showWelcome(ArrayList<Task> tasks) {
        String message = LINES;
        message += "Hello! I'm Duke\nFeed me some commands!\n";
        message += LINES;
        message += "Leftover tasks from before : \n";
        for (Task t : tasks) {
            message += t + "\n";
        }
        message += LINES;
        return message;
    }

    /**
     * Prints the UI for adding a task.
     *
     * @param t Task that is added.
     * @param n Number of tasks in the TaskList.
     * @return Task added message.
     */
    public String showAddedTask(Task t, int n) {
        return "Got it. I've added this task:\n  "
                + t
                + "\nNow you have " + n + " tasks in the list.\n";
    }

    /**
     * Prints the UI for deleting a task.
     *
     * @param t Task that is deleted.
     * @param n The index of the task that is deleted in TaskList.
     * @return Delete message.
     */
    public String showDeletedTask(Task t, int n) {
        return "Noted. I've removed this task:\n  "
                + t
                + "\nNow you have " + n + " tasks in the list.";
    }

    /**
     * Prints the UI for completing a task.
     *
     * @param t Task that is completed.
     * @return Done message.
     */
    public String showDoneTask(Task t) {
        return "Nice! I've marked this task as done:\n" + t;
    }

    /**
     * Prints the tasks within TaskList.
     *
     * @param list TaskList object.
     */
    public String printList(TaskList list) {
        String message = "Here are the task in your list:\n";
        for (int i = 0; i < list.tasks.size(); i++) {
            message += i + 1 + "." + list.tasks.get(i) + "\n";
        }
        return message;
    }

    /**
     * Prints the tasks that contains the given keyword.
     *
     * @param foundTaskList TaskList with filtered keyword.
     * @return Found tasks.
     */
    public String showFoundTask(ArrayList<Task> foundTaskList) {
        String message = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < foundTaskList.size(); i++) {
            message += i + 1 + "." + foundTaskList.get(i) + "\n";
        }
        return message;
    }

    /**
     * Returns a list of command the user can use.
     *
     * @return Help message.
     */
    public String showHelp() {
        return "Wof wof! Doggo heard meowing!\n"
                + "Meow : Doggo sends help\n"
                + "Todo : Tell Doggo your trouble\n"
                + "Deadline : Tell Doggo your worries\n"
                + "Event : Tell Doggo your schedule\n"
                + "List : Doggo tells you your task\n"
                + "Find : Doggo fetches your tasks\n"
                + "Done : Doggo notes your completed worries\n"
                + "Delete : Doggo clears your trouble\n"
                + "Bye : Doggo needs his afternoon nap\n";
    }
}
