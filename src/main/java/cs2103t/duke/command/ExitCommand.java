package cs2103t.duke.command;

import cs2103t.duke.file.Storage;
import cs2103t.duke.task.TaskList;
import cs2103t.duke.ui.Ui;

public class ExitCommand extends Command {
    public ExitCommand(){
        super.isExit = true;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.closeScanner();
        ui.dukeRespond("Bye. Hope to see you again soon!");
    }
}
