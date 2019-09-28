package duke.command;

import duke.repos.Repository;
import duke.repos.TaskRepo;
import duke.task.Task;

import java.text.SimpleDateFormat;

public class ExitCommand extends Command {

    public ExitCommand(String fullCommand, String[] splitCommand, SimpleDateFormat formatter) {
        super(fullCommand, splitCommand, formatter);
    }

    @Override
    public String execute(Repository<Task> taskRepo) {
        return "\nBye. Hope to see you again soon!";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
