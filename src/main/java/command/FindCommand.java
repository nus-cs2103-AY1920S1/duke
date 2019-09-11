package command;

import task.DukeException;
import task.TaskList;

public class FindCommand extends Command {
    private final String textInput;

    public FindCommand(String textInput) {
        this.textInput = textInput;
    }

    @Override
    public String execute() {
        if (isInvalidCommand(textInput, "find")) {
            throw new DukeException("Gomennasai! Keyword required!");
        }

        String keyWord = textInput.replaceFirst("find ", "");
        return TaskList.findTask(keyWord);
    }
}