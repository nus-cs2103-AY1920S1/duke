package duke.command;

import duke.repos.TaskRepo;

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

    public abstract String execute(TaskRepo taskRepo) throws ParseException, IOException;

    public abstract boolean isExit();
}