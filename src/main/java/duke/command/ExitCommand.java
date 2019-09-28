package duke.command;

import duke.backend.ListManager;
import duke.backend.Storage;
import duke.repos.TaskRepo;


import java.io.IOException;
import java.text.SimpleDateFormat;

public class ExitCommand extends Command {

    public ExitCommand(String fullCommand, String[] splitCommand, SimpleDateFormat formatter) {
        super(fullCommand, splitCommand, formatter);
    }

    @Override
    public String execute(TaskRepo taskRepo) {
        return "\nBye. Hope to see you again soon!";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
