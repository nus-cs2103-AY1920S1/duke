package commands;

import exceptions.DukeException;
import exceptions.InvalidDescriptionException;

import oop.Storage;
import oop.Ui;

import tasks.TaskList;

/**
 * The DeleteCommand class takes care of any commands that would
 * delete tasks from the current task list.
 */
public class DeleteCommand extends Command {
    /**
     * Constructs and initializes the attributes of a new DeleteCommand object.
     * @param commandText The portion of text that contains the details of the task.
     */
    public DeleteCommand(String commandText) {
        super();
        description = commandText;
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
        if (arrOfText.length < 2) {
            throw new InvalidDescriptionException("Wrong description");
        }

        int sizeOfList = task.getNumOfTasks();
        if (arrOfText[1].matches("^\\d+")) {
            int taskNum = Integer.parseInt(arrOfText[1]);
            if (taskNum > sizeOfList || taskNum < 1) {
                throw new InvalidDescriptionException("Wrong description");
            } else {
                String response = ui.showText(task.removeTask(taskNum));
                storage.writeToFile(task);
                return response;
            }
        } else {
            throw new InvalidDescriptionException("Wrong description");
        }
    }
}

