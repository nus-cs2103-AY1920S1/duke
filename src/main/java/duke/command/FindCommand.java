package duke.command;

import duke.repos.Repository;
import duke.repos.TaskRepo;
import duke.task.Task;
import duke.responses.ResponseGen;

import java.text.SimpleDateFormat;
import java.util.List;

public class FindCommand extends Command {

    public FindCommand(String fullCommand, String[] splitCommand, SimpleDateFormat formatter) {
        super(fullCommand, splitCommand, formatter);
        assert !fullCommand.equals("");
        assert splitCommand.length != 0;
    }

    @Override
    public String execute(Repository<Task> taskRepo) {
        List<Task> queriedList = taskRepo.find(this.splitCommand[1]);
        return ResponseGen.findResponse(queriedList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
