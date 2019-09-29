package duke.command;

import duke.repos.Repository;
import duke.repos.TaskRepo;
import duke.responses.ResponseGen;
import duke.task.Task;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class DoneCommand extends Command {

    /**
     * Constructor for DoneCommand.
     * @param fullCommand full String command.
     * @param splitCommand fullCommand.split
     * @param formatter formatter for Date
     */
    public DoneCommand(String fullCommand, String[] splitCommand, SimpleDateFormat formatter) {
        super(fullCommand, splitCommand, formatter);
        assert !fullCommand.equals("");
        assert splitCommand.length != 0;
    }

    @Override
    public String execute(Repository<Task> taskRepo) throws IOException {
        try {
            int index = Integer.parseInt(splitCommand[1]);
            taskRepo.done(index);
            return ResponseGen.doneResponse(taskRepo.getAll(), index);
        } catch (IOException e) {
            e.printStackTrace();
            return "Error!";
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

