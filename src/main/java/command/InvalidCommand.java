package command;

import utils.TaskList;
import utils.Storage;
import utils.Ui;

public class InvalidCommand extends Command {

    String errorMessage;

    public InvalidCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {

        String output = "";

        // Save output as String
        output += ui.getTopBorder();
        output += "\n\t" + errorMessage;
        output += ui.getBottomBorder();
        return output;

    }
}