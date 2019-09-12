package commands;

import components.Storage;
import components.TaskList;

public class PrintUserInputErrorCommand implements Command {
    private String message;

    public PrintUserInputErrorCommand(String message) {
        this.message = message;
    }

    @Override
    public String[] execute(Storage storage, TaskList taskList) {
        return new String[]{message};
    }
}
