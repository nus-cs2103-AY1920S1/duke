package duke;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Class to handle system output.
 */
public class Ui {

    public Ui() {
    }

    /**
     * Returns welcome message during startup.
     *
     * @return String Welcome message
     */
    String getWelcome() {
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
    String getLoadingError() {
        return "Failed to load save file. Please ignore this" +
                "message if this is your first time running this program!\n";
    }

    /**
     * Returns the error message of the DukeException being thrown in the standard format.
     *
     * @param s Error message of DukeException
     * @return String Error message of DukeException in the standard format
     */
    String getError(String s) {
        return getStandard(s);
    }

    /**
     * Returns all matching tasks to the keyword in the standard format.
     *
     * @param matches List of matching tasks
     * @return String Matching tasks in the standard format.
     */
    public String getMatches(ArrayList<String> matches) {
        String toReturn = getStandard("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matches.size(); ++i) {
            toReturn = toReturn.concat(getStandard(String.format("%d.%s", i + 1, matches.get(i).trim())));
        }
        return toReturn;
    }

    /**
     * Returns the most recent task that was added in the standard format.
     *
     * @param task    Most recent added task
     * @param lstSize Size of task list
     * @return String Most recent added task in the standard format
     */
    public String getAddedTask(String task, int lstSize) {
        String add = getStandard("Got it. I've added this task:\n");
        return add + getStandard(task) + getTaskSize(lstSize);
    }

    /**
     * Returns the most recent task that was deleted in the standard format.
     *
     * @param task    Most recent deleted task
     * @param lstSize Size of task list
     * @return String Most recent deleted task in the standard format
     */
    public String getDeletedTask(String task, int lstSize) {
        String del = getStandard("Noted. I've removed this task:\n");
        return del + getStandard(task) + getTaskSize(lstSize);
    }

    /**
     * Returns the most recent task that was marked as done in the standard format.
     *
     * @param task Most recent task that was marked as done
     * @return String Most recent done task in the standard format
     */
    public String getDoneTask(String task) {
        String done = getStandard("Nice! I've marked this task as done:\n");
        return done + getStandard(task);
    }

    /**
     * Returns the task list size for both add & delete in the standard format.
     *
     * @param size Size of task list
     * @return String Size of task list in the standard format
     */
    private String getTaskSize(int size) {
        return getStandard(String.format("Now you have %d tasks in the list.\n", size));
    }

    /**
     * Returns all the tasks in the TaskList and numbers them in the standard format.
     *
     * @param taskLst Tasks in the task list
     * @return String Numbered tasks in task list in the standard format
     */
    public String getAllTasks(LinkedList<String> taskLst) {
        String toReturn = getStandard("Here are the tasks in your list:\n");
        for (String task : taskLst) {
            toReturn = toReturn.concat(getStandard(task));
        }
        return toReturn;
    }

    /**
     * Returns the successful keyword replacement message in the standard format.
     *
     * @param oldKeyword Keyword to be replaced
     * @param newKeyword Keyword to replace
     * @return String Successful keyword replacement message
     */
    public String getReplaceKeywordMessage(String oldKeyword, String newKeyword) {
        return getStandard(String.format("Successfully replaced %s with %s", oldKeyword, newKeyword));
    }

    /**
     * Converts and returns any given String into the correct output format.
     *
     * @param string Input String
     * @return String Correct output format
     */
    private String getStandard(String string) {
        return "     " + string + "\n";
    }
}
