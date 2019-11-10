package commands;

import exception.DukeException;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

import java.util.ArrayList;

/**
 * Class that represents the find command.
 */
public class FindCommand implements Command {

    private String inputQuery;

    /**
     * Constructs the find command.
     *
     * @param ipt the string of search query
     */
    public FindCommand(String ipt) {
        inputQuery = ipt.toLowerCase();
    }

    /**
     * Indicates if the command is the exit command.
     *
     * @return false
     */
    public boolean isExitCommand() {
        return false;
    }

    /**
     * Executes the find command.
     * Searches within the tasklist if any of the tasks match the search query.
     * Search keyword is case-insensitive.
     * Displays all tasks that matches.
     *
     * @param tasks tasklist that stores all the tasks
     * @param ui ui object that deals with user interaction
     * @param storage storage object that deals with writing into the text file
     * @return string representation of the command response
     * @throws DukeException exception is thrown when no task is found.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> taskList = tasks.getTaskList();
        StringBuilder stringOfTasksFound = new StringBuilder("     Here are the matching tasks in your list:\n");
        int counter = 0;
        for (Task tk : taskList) {
            String curTaskDescription = tk.getDescription().toLowerCase();
            if (curTaskDescription.contains(inputQuery)) {
                stringOfTasksFound.append("     " + (counter + 1) + ". " + tk.toString() + "\n");
                counter++;
            }
        }
        if (counter == 0) {
            throw new DukeException("No tasks found.");
        }
        stringOfTasksFound.delete(stringOfTasksFound.length() - 1, stringOfTasksFound.length());
        return ui.showResultOfCommand(stringOfTasksFound.toString());
    }
}