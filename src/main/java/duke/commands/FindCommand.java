/**
 * This class represents a specific command of finding a particular task in Duke.
 */
package duke.commands;
import duke.exceptions.DukeException;
import duke.managers.Storage;
import duke.managers.TaskList;
import duke.managers.Ui;
import duke.tasks.Task;
import java.io.IOException;
import java.util.ArrayList;

public class FindCommand extends Command {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private String keyword;
    private ArrayList<Task> matchedTasks = new ArrayList<>();

    /**
     * This constructor processes the keyword that has been input into Duke chat bot.
     * @param keyword that the user has input to search all his tasks for
     */
    public FindCommand(String keyword) {
        this.keyword = keyword.trim();
    }

    /**
     * This method searches for tasks with that specific keyword and returns all matching tasks to the user.
     * @param tasks contains the data structure of Tasks stored in Duke
     * @param ui contains methods dealing with interaction with the user
     * @param storage contains methods to load and save information in the file
     * @exception DukeException is thrown when there is an error with the input
     * @exception IOException is thrown when there is an error saving the data in the hard disk
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        this.tasks = tasks;
        this.ui = ui;
        this.storage = storage;
        ArrayList<Task> allTasks = tasks.getAllTasks();
        for (Task task : allTasks) {
            String taskDescription = task.getDescription();
            String[] splitWords = taskDescription.split(" ");
            for (String word : splitWords) {
                if (word.trim().equals(keyword)) {
                    matchedTasks.add(task);
                    break;
                }
            }
        }
        printAllMatches();
    }

    /**
     * This method prints all the tasks that match the keyword given by the user. If there are no matching tasks,
     * the user will be advised as such.
     */
    private void printAllMatches() {
        if (matchedTasks.size() > 0) {
            ui.printLine("Here are the matching tasks in your list:");
            int index = 1;
            for (Task task : matchedTasks) {
                ui.printLine(index + "." + task.toString());
                index++;
            }
        } else {
            ui.printLine("Oops, there are no tasks with that keyword!");
        }

    }

    public boolean isExit() {
        return false;
    }
}
