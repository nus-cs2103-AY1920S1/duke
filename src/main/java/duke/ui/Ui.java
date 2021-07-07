package duke.ui;

/**
 * Contains responses and interactions with the user.
 */
public class Ui {

    private static final String MSG_REMINDER = "Nezuko has found a few urgent tasks:\n";
    private static final String MSG_BYE = "Bye-bye~ Hope to see you again soon!\n";
    private static final String MSG_HI = "Hi! Say Something to me!\n";
    private static final String MSG_DONE = "Nice! I've marked this task as done: \n ";
    private static final String MSG_SHOW_ADD = "Got it. I've added this task: \n ";
    private static final String MSG_LIST = "Here are the tasks in your list:\n";
    private static final String MSG_SEARCH = "Here are the matching tasks in your list:\n";
    private static final String MSG_REMOVE = "Noted. I've removed this task:\n ";
    private static final String MSG_LOADING_ERROR = "Oops, something went wrong with Nezuko ><\n";
    private static final String MSG_TASK_NUM_SINGULAR = "Now you have %d task in the list.\n";
    private static final String MSG_TASK_NUM_PLURAL = "Now you have %d tasks in the list.\n";
    private static final String MSG_CLEAR = "Great! I've removed all your tasks!\n";
    private static final String MSG_HELP = "Nezuko understands the following commands:\n"
            + "  - done <index>: mark task <index> as done.\n"
            + "  - delete <index>: delete task <index>.\n"
            + "  - todo <task description>: adds a todo task to you task list.\n"
            + "  - deadline <task description>: adds a deadline task to your task list.\n"
            + "  - event <task description>: adds an event task to ypur task list.\n"
            + "  - find <keyword>: look up fot tasks containing the <keyword>.\n"
            + "  - list: view the full list of tasks.\n"
            + "  - help: check out the available commands.\n"
            + "  - bye: exit the programme.\n"
            + "  - clear: remove all tasks from the task list.\n"
            + "You can go to the Task Master Nezuko User Guide at https://xiaoyu-nus.github.io/duke/"
            + "for more information.";

    private MainWindow mw;

    public Ui(MainWindow mw) {
        this.mw = mw;
    }

    public void showGoodbye() {
        mw.displayMsg(MSG_BYE);
    }

    public void sayHi() {
        mw.displayMsg(MSG_HI);
    }

    /**
     * Displays a finished task.
     *
     * @param doneTask Task done.
     */
    public void showDone(String doneTask) {
        mw.displayMsg(MSG_DONE + doneTask + "\n");
    }

    /**
     * Displays a newly added task.
     *
     * @param task Task added.
     */
    public void showAdd(String task, int num) {
        mw.displayMsg(MSG_SHOW_ADD + task + "\n"
                + showCount(num));
    }

    /**
     * Displays a header for the task list contends.
     */
    public void showList(String str) {
        mw.displayMsg(MSG_LIST + str);
    }

    /**
     * Displays a header for the task search.
     */
    public void showSearch(String str) {
        mw.displayMsg(MSG_SEARCH + str);
    }

    /**
     * Displays a task removed from the task list.
     *
     * @param removed Task removed.
     */
    public void showRemove(String removed, int num) {
        mw.displayMsg(MSG_REMOVE + removed + "\n"
                + showCount(num));
    }

    public void showError(String message) {
        mw.displayMsg(message + "\n");
    }

    /**
     * Displays and error message when the file is unable to be loaded.
     */
    public void showLoadingError() {
        mw.displayMsg(MSG_LOADING_ERROR);
    }

    private String showCount(int numOfTask) {
        if (numOfTask < 2) {
            return String.format(MSG_TASK_NUM_SINGULAR, numOfTask);
        } else {
            return String.format(MSG_TASK_NUM_PLURAL, numOfTask);
        }
    }

    /**
     * Displays a header for the clear command.
     */
    public void showClearList() {
        mw.displayMsg(MSG_CLEAR);
    }

    /**
     * Displays the sorted tasks as a reminder.
     *
     * @param sortedTask String representation of the sorted tasks.
     */

    public void showReminder(String sortedTask) {
        mw.displayMsg(MSG_REMINDER + sortedTask);
    }

    public void showHelp() {
        mw.displayMsg(MSG_HELP);
    }
}

