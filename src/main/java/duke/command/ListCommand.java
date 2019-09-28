package duke.command;

import duke.repos.Repository;
import duke.repos.TaskRepo;
import duke.responses.ResponseGen;
import duke.task.Task;

import java.text.SimpleDateFormat;

public class ListCommand extends Command {

    public ListCommand(String fullCommand, String[] splitCommand, SimpleDateFormat formatter) {
        super(fullCommand, splitCommand, formatter);
        assert !fullCommand.equals("");
        assert splitCommand.length != 0;
    }

    @Override
    public String execute(Repository<Task> taskRepo) {
        return ResponseGen.listResponse(taskRepo.getAll());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
