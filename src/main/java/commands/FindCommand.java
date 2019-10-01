package commands;

import exceptions.DukeException;

import exceptions.InvalidDescriptionException;

import storage.Storage;

import ui.Ui;

import tasks.Task;
import tasks.TaskList;

/**
 * The FindCommand class takes care finding specific task stated
 * by user input.
 */
public class FindCommand extends Command {
    /**
     * Constructs and initializes the attributes of a new FindCommand object.
     * @param description The description of the specified task.
     */
    public FindCommand(String description) {
        super();
        this.description = description;
    }

    /**
     * Carries out the command and does the necessary changes and prompts
     * user about the change.
     * @param task Current list of tasks.
     * @param ui Ui for user interactions.
     * @param storage Storage for writing of information to text file.
     * @throws DukeException Possibility of throwing a DukeException due to
     *      an exception occurring in the running of the application.
     */
    public String execute(TaskList task, Ui ui, Storage storage) throws DukeException {
        String[] arrOfText = description.split(" ", 2);
        if (arrOfText.length < 2 || arrOfText[1].matches("\\s*")) {
            throw new InvalidDescriptionException("Wrong description");
        }

        String pattern = String.format(".*%s.*", arrOfText[1]);
        TaskList tempTaskList = new TaskList();
        for (Task t : task.getTaskList()) {
            if (t.getDescription().matches(pattern)) {
                tempTaskList.addTask(t);
            }
        }
        return ui.showText(tempTaskList.printMatchingTasks());
    }
}

