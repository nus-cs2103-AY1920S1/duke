package duke.command;

import duke.task.DukeException;
import duke.task.TaskList;

/**
 * The DeleteCommand class defines the behaviour of a delete command.
 * 
 * @author Joel Loong
 */
public class DeleteCommand extends Command {
    private final String textInput;

    public DeleteCommand(String textInput) {
        this.textInput = textInput;
    }

    @Override
    public String execute() {
        if (isInvalidCommand(textInput, "delete")) {
            throw new DukeException("Gomennasai! Index required.");
        }

        int deletedIndex = Integer.parseInt(textInput.replaceFirst("delete ", "")) - 1;
        if (isInvalidIndex(deletedIndex)) {
            throw new DukeException("Gomennasai! Index not found.");
        }

        return TaskList.deleteTask(deletedIndex);
    }
}