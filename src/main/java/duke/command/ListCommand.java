package duke.command;

import duke.ResponseGen;
import duke.backend.ListManager;
import duke.backend.Storage;
import duke.repos.TaskRepo;
import duke.task.Task;

import java.text.SimpleDateFormat;
import java.util.List;

public class ListCommand extends Command {

    public ListCommand(String fullCommand, String[] splitCommand, SimpleDateFormat formatter) {
        super(fullCommand, splitCommand, formatter);
    }

    @Override
    public String execute(TaskRepo taskRepo) {
        return ResponseGen.listResponse(taskRepo.getAll());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
