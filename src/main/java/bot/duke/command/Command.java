package bot.duke.command;

import java.text.SimpleDateFormat;

import bot.duke.storage.Storage;
import bot.duke.task.TaskList;
import bot.duke.ui.Ui;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public abstract boolean isExit();

}
