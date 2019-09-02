package commands;

import java.util.ArrayList;
import java.util.Arrays;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

import tasks.Task;

/**
 * FindCommand is a class that finds task items
 * from the list of tasks based on a keyword given via user input.
 */
public class FindCommand extends Command {

    /**
     *  Constructor for FindCommand.
     *  Boolean isExit is set to false because
     *  program should not terminate after command is executed.
     *
     * @param commandArr String array containing the split text retrieved from user input.
     */
    public FindCommand(String[] commandArr) {
        this.commandArr = commandArr;
        isExit = false;
    }

    /**
     * Returns a boolean value detailing whether the String description
     * contains keyword as a word.
     *
     * @param description the description (of the task)
     * @param keywords String[] containing the keywords to be matched
     * @return boolean value detailing whether a match was found
     */
    public static boolean containsKeyWords(String description, String[] keywords) {
        ArrayList<String> al = new ArrayList<>(Arrays.asList(keywords));
        ArrayList<String> desc = new ArrayList<>(Arrays.asList(description.split(" ")));
        return desc.containsAll(al);
    }

    /**
     * Finds tasks from the list of tasks that
     * match the keyword specified by the user.
     *
     * @param tasks the TaskList object storing all recorded Tasks.
     * @param ui the Ui object dealing with user interaction.
     * @param storage the Storage object that reads from and writes to the file.
     * @return String output reply from Duke.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String[] keywords = commandArr[1].split(" ");
        ArrayList<Task> taskLst = tasks.getTaskLst();
        String store = "     Here are the matching tasks in your list:\n";
        for (int i = 0; i < taskLst.size(); i++) {
            if (containsKeyWords(taskLst.get(i).getDescription(), keywords)) {
                store += String.format("     %d.%s\n",
                        i + 1, taskLst.get(i));
            }
        }
        return store;
    }

}
