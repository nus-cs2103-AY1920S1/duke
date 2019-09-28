package duke.command;

import duke.repos.TaskRepo;
import duke.responses.ResponseGen;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class DoneCommand extends Command {

    public DoneCommand(String fullCommand, String[] splitCommand, SimpleDateFormat formatter) {
        super(fullCommand, splitCommand, formatter);
        assert !fullCommand.equals("");
        assert splitCommand.length != 0;
    }

    @Override
    public String execute(TaskRepo taskRepo) throws IOException {
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

