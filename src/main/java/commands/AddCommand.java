package commands;

import exceptions.DukeException;
import exceptions.InvalidDescriptionException;

import oop.Storage;
import oop.Ui;

import tasks.Deadlines;
import tasks.Event;
import tasks.TaskList;
import tasks.ToDos;

/**
 * The AddCommand class takes care of any commands that would
 * add tasks onto the current task list.
 */
public class AddCommand extends Command {

    /**
     * Constructs and initializes the attributes of a new AddCommand object.
     * @param commandText The portion of text that contains the details of the task.
     */
    public AddCommand(String commandText) {
        super();
        this.description = commandText;
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

        switch (arrOfText[0]) {
        case "todo":
            String response = ui.showText(task.addTask(new ToDos(arrOfText[1])));
            storage.writeToFile(task);
            return response;

        case "deadline":
            String[] input2 = arrOfText[1].trim().split("/by");
            if (input2.length != 2) {
                throw new InvalidDescriptionException("Wrong description");
            }
            String response2 = ui.showText(task.addTask(new Deadlines(input2[0], input2[1])));
            storage.writeToFile(task);
            return response2;

        case "event":
            String[] input3 = arrOfText[1].trim().split("/at");
            if (input3.length != 2) {
                throw new InvalidDescriptionException("Wrong description");
            }
            String response3 = ui.showText(task.addTask(new Event(input3[0], input3[1])));
            storage.writeToFile(task);
            return response3;

        default:
            return "";
        }
    }
}

