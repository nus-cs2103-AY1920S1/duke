package command;

import task.TaskList;
import util.DukeException;
import util.Storage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Command {

    boolean isExit;
    protected String command;

    Command() {
        isExit = false;
    }

    public boolean isExit() {
        return isExit;
    }

    public abstract void executeCommand(TaskList taskList, Storage storage) throws DukeException, IOException;

    public String convertDate(String date) {
        if (date.indexOf('/') > -1) {
            if (date.indexOf(' ') > -1) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy Hmm");
                LocalDateTime newDate = LocalDateTime.parse(date, formatter);
                return newDate.format(DateTimeFormatter.ofPattern("MMM dd H:mma, yyyy"));
            } else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
                LocalDateTime newDate = LocalDateTime.parse(date, formatter);
                return newDate.format(DateTimeFormatter.ofPattern("MMM dd, yyyy"));
            }
        }
        return date;
    }
}
