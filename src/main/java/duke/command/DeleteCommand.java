package duke.command;

import duke.task.*;
import duke.io.*;

public class DeleteCommand extends Command {
    private String input;

    public DeleteCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws NumberFormatException {
        ui.showLine();
        int value = Parser.getIndex(input, taskList.size());
        ui.out("Noted. I've removed this task:");
        ui.out(taskList.get(value - 1).toString());
        taskList.remove(value - 1);
        ui.out("Now you have " + taskList.size() + " tasks in the list.");
        ui.showLine();
    }
}
