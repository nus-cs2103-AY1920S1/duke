package duke.command;

import duke.ResponseGen;
import duke.backend.ListManager;
import duke.backend.Storage;
import duke.repos.TaskRepo;
import duke.task.Task;

import java.text.SimpleDateFormat;
import java.util.List;

public class FindCommand extends Command {

    public FindCommand(String fullCommand, String[] splitCommand, SimpleDateFormat formatter) {
        super(fullCommand, splitCommand, formatter);
    }

    @Override
    public String execute(TaskRepo taskRepo) {
        List<Task> queriedList = taskRepo.find(this.splitCommand[1]);
        return ResponseGen.findResponse(queriedList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
