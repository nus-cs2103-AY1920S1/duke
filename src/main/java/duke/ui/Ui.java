package duke.ui;

import duke.MainWindow;

/**
 * Contains responses and interactions with the user.
 */
public class Ui {

    private static final String INDENT = "     ";
    private MainWindow mw;

    public Ui(MainWindow mw) {
        this.mw = mw;
    }

    public void showGoodbye() {
        mw.displayMsg("バイバイ~ Hope to see you again soon!\n");
        System.out.printf(INDENT + "Bye. Hope to see you again soon!\n");
    }

    public void sayHi() {
        mw.displayMsg("Hi! Say Something to me!\n");
        System.out.printf(INDENT + "Hi :-D Say Something to me!\n");
    }

    /**
     * Displays a finished task.
     *
     * @param doneTask Task done.
     */
    public void showDone(String doneTask) {
        mw.displayMsg("Nice! I've marked this task as done: \n" + " " + doneTask + "\n");
        System.out.printf(INDENT + "Nice! I've marked this task as done: \n" + INDENT + " " + doneTask + "\n");

    }

    /**
     * Displays a newly added task.
     *
     * @param task Task added.
     */
    public void showAdd(String task, int num) {
        mw.displayMsg("Got it. I've added this task: \n" + " " + task + "\n"
                + showCount(num));
        System.out.printf(INDENT + "Got it. I've added this task: \n" + INDENT + " " + task + "\n");
    }

    /**
     * Displays a header for the task list contends.
     */
    public void showList(String str) {
        mw.displayMsg("Here are the tasks in your list:\n" + str);
        System.out.printf(INDENT + "Here are the tasks in your list:\n");
    }

    /**
     * Displays a header for the task search.
     */
    public void showSearch(String str) {
        mw.displayMsg("Here are the matching tasks in your list:\n" + str);
        System.out.printf(INDENT + "Here are the matching tasks in your list:\n");
    }

    /**
     * Displays a task removed from the task list.
     *
     * @param removed Task removed.
     */
    public void showRemove(String removed, int num) {
        mw.displayMsg("Noted. I've removed this task:\n" + " " + removed + "\n"
                + showCount(num));
        System.out.printf(INDENT + "Noted. I've removed this task:\n" + INDENT + " " + removed + "\n");
    }

    /**
     * Displays a no result found message for search.
     */
    public void showNotFound() {
        mw.displayMsg("Hummm, nothing has been found.\n");
        System.out.printf(INDENT + "Hummm, nothing has been found.\n");
    }

    public void showError(String message) {
        mw.displayMsg(message + "\n");
        System.out.printf(INDENT + message + "\n");
    }

    /**
     * Displays and error message when the file is unable to be loaded.
     */
    public void showLoadingError() {
        mw.displayMsg("Oops, something went wrong with Nezuko ><\n");
        System.out.printf(INDENT + "Oops, something went wrong\n");
    }

    private String showCount(int numOfTask) {
        if (numOfTask < 2) {
            return "Now you have " + numOfTask + " task in the list.\n";
        } else {
            return "Now you have " + numOfTask + " tasks in the list.\n";
        }
    }

    /**
     * Displays a header for the clear command.
     */
    public void showClearList() {
        mw.displayMsg("Great! I've removed all your tasks!\n");
        System.out.printf(INDENT + "Great! I've removed all your tasks!\n");
    }

    /**
     * Displays the task.
     *
     * @param index Index of the task in the task list.
     * @param task  Task to be displayed.
     */
    public void showTask(int index, String task) {
        mw.displayMsg(" " + index + ". " + task + "\n");
        System.out.printf(INDENT + " " + index + ". " + task + "\n");
    }

    /**
     * Displays the sorted tasks as a reminder.
     *
     * @param sortedTask String representation of the sorted tasks.
     */
    public void showReminder(String sortedTask) {
        mw.displayMsg("Nezuko has found a few urgent tasks:\n" + sortedTask);
        System.out.printf("Nezuko has found a few urgent tasks:\n" + sortedTask);
    }
}

