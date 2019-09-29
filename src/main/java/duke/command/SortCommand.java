package duke.command;

import duke.repos.Repository;
import duke.responses.ResponseGen;
import duke.task.Task;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class SortCommand extends Command {
    public SortCommand(String fullCommand, String[] splitCommand, SimpleDateFormat formatter) {
        super(fullCommand, splitCommand, formatter);
    }

    @Override
    public String execute(Repository<Task> taskRepo) throws ParseException, IOException {
        List<Task> sortedList = taskRepo.sort();
        return ResponseGen.sortResponse(sortedList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
