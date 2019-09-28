package duke.command;

import duke.backend.ListManager;
import duke.backend.Storage;


import java.io.IOException;

public class ExitCommand extends Command {

    public ExitCommand(String fullCommand, String[] splitCommand) {
        super(fullCommand, splitCommand);
    }

    @Override
    public String execute(ListManager listManager, Storage storage) {
        try {
            storage.save(listManager.actualList);
        } catch (IOException e) {
            System.out.println("Failed to save:" + e.getMessage());
        }
        return "\nBye. Hope to see you again soon!";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
