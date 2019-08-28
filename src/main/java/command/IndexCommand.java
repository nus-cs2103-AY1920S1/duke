package main.java.command;

import main.java.TaskList;
import main.java.Ui;
import main.java.Storage;

public class IndexCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}