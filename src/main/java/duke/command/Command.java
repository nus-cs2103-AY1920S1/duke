package duke.command;

import duke.repos.Repository;
import duke.repos.TaskRepo;
import duke.task.Task;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public abstract class Command {

    String fullCommand;
    String[] splitCommand;
    SimpleDateFormat formatter;

    public Command(String fullCommand, String[] splitCommand, SimpleDateFormat formatter) {
        this.fullCommand = fullCommand;
        this.splitCommand = splitCommand;
        this.formatter = formatter;
    }

    public abstract String execute(Repository<Task> taskRepo) throws ParseException, IOException;

    public abstract boolean isExit();
}