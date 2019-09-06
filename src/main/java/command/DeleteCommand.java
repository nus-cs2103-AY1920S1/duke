package command;

import task.DukeException;
import task.TaskList;

public class DeleteCommand extends Command {
    private final String textInput;

    public DeleteCommand(String textInput) {
        this.textInput = textInput;
    }

    @Override
    public String execute() {
        if (isInvalidCommand(textInput, "delete")) {
            throw new DukeException("OOPS!!! Index required.");
        }

        int deletedIndex = Integer.parseInt(textInput.replaceFirst("delete ", "")) - 1;
        if (isInvalidIndex(deletedIndex)) {
            throw new DukeException("OOPS!!! Index not found.");
        }

        return TaskList.deleteTask(deletedIndex);
    }
}