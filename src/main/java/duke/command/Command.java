package duke.command;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public abstract class Command {
    String commandType;

    public String getCommandType(){
        return commandType;
    }
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);
    public abstract boolean isExit();
}
