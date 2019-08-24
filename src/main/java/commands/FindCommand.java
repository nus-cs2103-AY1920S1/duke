package commands;

import java.util.ArrayList;
import java.util.Scanner;
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
     * @param fullCommand the line of user input.
     */
    public FindCommand(String fullCommand) {
        this.fullCommand = fullCommand;
        isExit = false;
    }

    /**
     * Returns a boolean value detailing whether the String description
     * contains keyword as a word.
     *
     * @param description the description (of the task)
     * @param keyword the keyword to be matched
     * @return boolean value detailing whether a match was found
     */
    public static boolean containsWord(String description, String keyword) {
        Scanner sc = new Scanner(description);
        while (sc.hasNext()) {
            if (keyword.equals(sc.next())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Finds tasks from the list of tasks that
     * match the keyword specified by the user.
     *
     * @param tasks the TaskList object storing all recorded Tasks.
     * @param ui the Ui object dealing with user interaction.
     * @param storage the Storage object that reads from and writes to the file.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Scanner sc = new Scanner(fullCommand);
        sc.next();
        String keyword = sc.next();
        ArrayList<Task> taskLst = tasks.getTaskLst();
        System.out.println("     Here are the matching tasks in your list:");
        for (int i = 0; i < taskLst.size(); i++) {
            if (containsWord(taskLst.get(i).getDescription(), keyword)) {
                System.out.printf("     %d.%s\n",
                        i + 1, taskLst.get(i));
            }
        }
    }

}
