package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

import java.text.SimpleDateFormat;

public abstract class Command {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public abstract void execute(TaskList tasks, Ui ui);

    public abstract boolean isExit();

}
