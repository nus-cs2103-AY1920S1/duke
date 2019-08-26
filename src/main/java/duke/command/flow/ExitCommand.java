package duke.command.flow;

import duke.command.Command;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try{
            storage.save(tasks);
        } catch (IOException e){
            ui.exposeError(e.getMessage());
        }
        ui.printExitMsg();
    }

    @Override
    public boolean isExit() {
        return true;
    }

}
