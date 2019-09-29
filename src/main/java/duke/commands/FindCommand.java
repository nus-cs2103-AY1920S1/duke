/**
 * This class represents a specific command of finding a particular task in Duke.
 */

package duke.commands;

import duke.managers.Storage;
import duke.managers.TaskList;
import duke.managers.Ui;

import duke.tasks.Task;

import java.util.ArrayList;

public class FindCommand extends Command {
    private Ui ui;
    private TaskList tasks;
    private String keyword;
    private ArrayList<Task> matchedTasks = new ArrayList<>();

    /**
     * Processes the keyword that has been input into Duke chat bot.
     *
     * @param keyword that the user has input to search all his tasks for
     */
    public FindCommand(String keyword) {
        this.keyword = keyword.trim();
    }

    /**
     * Executes command to search for tasks with that specific keyword and return all matching tasks to the user.
     *
     * @param tasks contains the data structure of Tasks stored in Duke
     * @param ui contains methods dealing with interaction with the user
     * @param storage contains methods to load and save information in the file
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        this.ui = ui;
        this.tasks = tasks;
        findAllMatches();
        return printAllMatches();
    }

    /**
     * Searches for tasks with that specific keyword.
     */
    public void findAllMatches() {
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
    }

    /**
     * Prints all the tasks that match the keyword given by the user. If there are no matching tasks,
     * the user will be advised as such.
     *
     * @return String containing a list of all matches
     */
    private String printAllMatches() {
        if (matchedTasks.size() > 0) {
            String printedLines = "Here are the matching tasks in your list:" + "\n";
            printedLines += printMatchesFromList();
            return this.ui.printLine(printedLines.trim());
        } else {
            return this.ui.printLine("Oops, there are no tasks with that keyword!");
        }
    }

    /**
     * Prints each task that matches the keyword given by the user.
     *
     * @return String all matching tasks in storage
     */
    private String printMatchesFromList() {
        int index = 1;
        String printedLines = "";
        for (Task task : matchedTasks) {
            printedLines += index + "." + task.toString() + "\n";
            index++;
        }
        return printedLines;
    }

    public boolean isExit() {
        return false;
    }
}
