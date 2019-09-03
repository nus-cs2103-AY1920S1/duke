package duke;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 */
public class Ui {
    private Scanner sc;

    public Ui() {
    }

    /**
     * Returns welcome message during startup.
     *
     * @return String Welcome message
     */
    public String getWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return "Hello from\n" + logo + getStandard("Hello! I'm Duke\n")
                + getStandard("What can I do for you?\n");
    }

    /**
     * Returns farewell message when "bye" is entered.
     *
     * @return String Farewell message
     */
    public String getExit() {
        return getStandard("Bye. Hope to see you again soon!");
    }

    /**
     * Returns the loading error message.
     *
     * @return String Loading error message
     */
    public String getLoadingError() {
        return "Failed to load save file. Please ignore this" +
                "message if this is your first time running this program!\n";
    }

//    /**
//     * Returns the standard line output for visual differentiation between user input and system output.
//     *
//     * @return String Line
//     */
//    private String getLine() {
//        return "    ____________________________________________________________\n";
//    }

    /**
     * Reads a line of user input via a Scanner variable whenever the "enter" key is pressed.
     *
     * @return
     */
//    public String readCommand() {
//        return sc.nextLine();
//    }

    /**
     * Returns the error message of the DukeException being thrown.
     *
     * @param s
     * @return String Error message of DukeException
     */
    public String getError(String s) {
        return getStandard(s);
    }

    /**
     * Returns all matching tasks to the keyword.
     *
     * @param matches
     * @return String Matching tasks
     */
    public String getMatches(ArrayList<String> matches) {
        String toReturn = getStandard("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matches.size(); ++i) {
            toReturn = toReturn.concat(getStandard(String.format("%d.%s", i + 1, matches.get(i).trim())));
        }
        return toReturn;
    }

    /**
     * Returns the most recent task that was added.
     *
     * @param task
     * @param lstSize
     * @return String Most recent added task
     */
    public String getAddedTask(String task, int lstSize) {
        String add = getStandard("Got it. I've added this task:\n");
        return add + getStandard(task) + getTaskSize(lstSize);
    }

    /**
     * Returns the most recent task that was deleted.
     *
     * @param task
     * @param lstSize
     * @return String Most recent deleted task
     */
    public String getDeletedTask(String task, int lstSize) {
        String del = getStandard("Noted. I've removed this task:\n");
        return del + getStandard(task) + getTaskSize(lstSize);
    }

    /**
     * Returns the most recent task that was marked as done.
     *
     * @param task
     * @return String Most recent done task
     */
    public String getDoneTask(String task) {
        String done = getStandard("Nice! I've marked this task as done:\n");
        return done + getStandard(task);
    }

    /**
     * Returns the task list size for both add & delete.
     *
     * @param size
     * @return String Size of task list
     */
    private String getTaskSize(int size) {
        return getStandard(String.format("Now you have %d tasks in the list.\n", size));
    }

    /**
     * Returns all the tasks in the TaskList and numbers them.
     *
     * @param taskLst
     * @return String Numbered tasks in task list
     */
    public String getAllTasks(LinkedList<String> taskLst) {
        String toReturn = getStandard("Here are the tasks in your list:\n");
        for (String task : taskLst) {
            toReturn = toReturn.concat(getStandard(task));
        }
        return toReturn;
    }

    /**
     * Converts and returns any given String into the correct output format.
     *
     * @param string
     * @return String correct output format
     */
    private String getStandard(String string) {
        return "     " + string + "\n";
    }
}
