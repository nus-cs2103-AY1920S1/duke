package duke.command;

import duke.task.DukeException;
import duke.task.TaskList;

/**
 * The DoneCommand class defines the behaviour of a done command.
 * 
 * @author Joel Loong
 */
public class DoneCommand extends Command {
    private final String textInput;

    public DoneCommand(String textInput) {
        this.textInput = textInput;
    }

    @Override
    public String execute() {
        if (isInvalidCommand(textInput, "done")) {
            throw new DukeException("Gomennasai! Index required.");
        }

        int doneIndex = Integer.parseInt(textInput.replaceFirst("done ", "")) - 1;
        if (isInvalidIndex(doneIndex)) {
            throw new DukeException("Gomennasai! Index not found.");
        }

        return TaskList.doneTask(doneIndex);
    }
}