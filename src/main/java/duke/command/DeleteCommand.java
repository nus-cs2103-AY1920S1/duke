package duke.command;

import duke.repos.TaskRepo;
import duke.responses.ResponseGen;
import duke.task.Task;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class DeleteCommand extends Command {

    public DeleteCommand(String fullCommand, String[] splitCommand, SimpleDateFormat formatter) {
        super(fullCommand, splitCommand, formatter);
        assert !fullCommand.equals("");
        assert splitCommand.length != 0;
    }

    @Override
    public String execute(TaskRepo taskRepo) throws IOException {
        try {
            int index = Integer.parseInt(splitCommand[1]);
            Task removed = taskRepo.delete(index);
            return ResponseGen.deleteResponse(taskRepo.getAll(), removed);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

