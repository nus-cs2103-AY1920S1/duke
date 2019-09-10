package duke.command;

import duke.Model;
import duke.Storage;
import duke.io.UiOutput;

public class UndoCommand extends Command {
    public UndoCommand(String[] args) {
        super(args);
    }

    @Override
    public String getName() {
        return "undo";
    }

    @Override
    public void execute(Model model, UiOutput uiOutput, Storage storage) {
        try {
            Command undoneCommand = model.undo();
            uiOutput.say("Okay, I've undoed the last '" + undoneCommand.getName() + "' command.");
        } catch (IllegalStateException e) {
            uiOutput.oops("I don't have anything to undo!");
        }
    }
}
