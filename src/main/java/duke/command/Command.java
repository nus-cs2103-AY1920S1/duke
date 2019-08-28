package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;


// General Command class
public abstract class Command {
    private COMMAND_TYPE commandType;
    private Task pendingTask;

    // Init new command object
    public Command(String command, Task pendingTask) {
        // Exception handling
        // IllegalArgumentException - if the specified enum type has no constant with the specified name, or the specified class object does not represent an enum type
        // NullPointerException - if enumType or name is null

        try {
            this.commandType = COMMAND_TYPE.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException i) {
            throw new IllegalArgumentException();
        } catch (NullPointerException n) {
            throw new NullPointerException();
        }

        if (pendingTask == null) throw new NullPointerException();
        else this.pendingTask = pendingTask;
    }

    public abstract void execute(TaskList list, Ui ui, Storage storage) throws IOException;

    public abstract boolean isExit();

    public Task getPendingTask() {
        return pendingTask;
    }

    public COMMAND_TYPE getCommandType() {
        return commandType;
    }

}


