package duke;

import duke.data.DukeData;
import duke.data.TaskList;
import duke.task.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Ui class handles all user interactions and is responsible for returning the correct
 * String representations of the output for the user.
 */
public class Ui {
    private static final String LOGO = "____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String LINE = "________________________________________________";

    private Scanner sc;

    /**
     * Creates a new Ui object.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Checks if there is any new input made by the user.
     * @return true is there is more input, and false otherwise
     */
    public boolean hasNextInput() {
        return this.sc.hasNext();
    }

    public String getCommand() {
        return this.sc.nextLine();
    }

    /**
     * Greets the users, and asks users what they want Duke to do.
     * @return a String of Greetings.
     */
    public String showIntro() {
        String out = String.format("%s%n Hello! I am Duke%n " +
                "What can I do for you?", LOGO);
        return addLines(out);
    }

    /**
     * Lists out all the tasks the user has added, be it ToDo, Deadlines or Events,
     * in the order of input.
     * @param tasks an ArrayList which consists of the Task objects
     * @return a String representation of all Tasks in the list
     */
    public String showList(ArrayList<Task> tasks) {
        StringBuilder s = new StringBuilder();
        s.append("Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            s.append(System.getProperty("line.separator"));
            s.append(" ").append(i).append(".");
            Task t = tasks.get(i - 1);
            s.append(t.toString());
        }
        return addLines(s.toString());
    }

    /**
     * Bids the user GoodBye after the user is done using Duke.
     * @return a string of farewell words
     */
    public String showFarewell() {
        String bye = String.format("GoodBye! Hope to see you again soon!");
        return addLines(bye);
    }

    /**
     * Informs the user of the task added.
     * @param task the Task to be added, which can be a ToDo, Deadline, or Event
     * @param list the TaskList which contains all Tasks in the list
     * @return a String of information notifying the user of the added task
     */
    public String showTaskAdded(Task task, TaskList list) {
        String added = String.format("Got it! I've added this task:" +
                        "%n   %s%n Now you have %d task in the list.",
                task.toString(), list.getSize());
        return addLines(added);
    }

    /**
     * Notifies the user of the task marked as done.
     * @param taskNo the task number, in the order of input
     * @param list the TaskList which contains all Tasks in the list
     * @return a string to inform user of the task marked as done
     */
    public String showDone(int taskNo, TaskList list) {
        Task t = list.getTask(taskNo - 1);
        String done = String.format("Nice! I've marked this task as done:%n %s",
                t.toString());
        return addLines(done);
    }

    /**
     * Notifies the user of the Task removed.
     * @param index the task number, in the order of input
     * @param list the list from which the Task is to be deleted from
     * @return a string to inform user of the task removed from the list
     */
    public String showDelete(int index, TaskList list) {
        Task t = list.getTask(index);
        String del = String.format("Noted. I've removed this task:%n   %s%n" +
                        " Now you have %d tasks in the list.",
                t.toString(), list.getSize() - 1);
        return addLines(del);
    }

    /**
     * Shows the user his data that is stored in the hard disk.
     * @param dukeData the DukeData object that stores the user's data from Duke program
     * @return a String representation of the data present in the local Storage file
     * @throws IOException if an I/O Exception occurs
     */
    public String showData(DukeData dukeData) throws IOException {
        String data = String.format("This is the list of tasks stored in your local storage disc:%s",
                dukeData.showDataFile());
        return addLines(data);
    }

    /**
     * This method searches the TaskList for the tasks that contain the relevant keyword,
     * and displays the details of the tasks.
     * @param keyword the word that users key in to find the task with the keyword
     * @param list the TaskList to search for the tasks with the keyword
     * @return a String representation of the list of tasks containing the keyword
     */
    public String showFind(String keyword, TaskList list) {
        StringBuilder found = new StringBuilder("Here are the matching tasks in your list:");
        ArrayList<Task> taskList = list.getList();
        int count = 0;
        for (int i = 0; i < list.getSize(); i++) {
            Task t = taskList.get(i);
            String desc = t.getDesc();
            if (desc.contains(keyword)) {
                count++;
                found.append(System.getProperty("line.separator"));
                found.append(" ").append(count).append(".");
                found.append(t.toString());
            }
        }
        return addLines(found.toString());
    }

    /**
     * Adds a line before and after every command.
     * @param cmd the command output
     * @return a String representation of the output with the lines
     */
    public static String addLines(String cmd) {
        String out = String.format("%s%n%s%n", cmd, LINE);
        return out;
    }
}
