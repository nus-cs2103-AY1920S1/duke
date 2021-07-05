package duke.command;

import duke.task.TaskList;

/**
 * The Command abstract class defines the behaviour of commands that extends it.
 * 
 * @author Joel Loong
 */
public abstract class Command {
    public abstract String execute();

    protected boolean isInvalidCommand(String textInput, String command) {
        assert command != null : "Command is null";
        return textInput.equals(command) || textInput.equals(command + " ");
    }

    protected boolean isInvalidIndex(int index) {
        assert (Integer) index != null : "Index is null";
        return index < 0 || index >= TaskList.getCounter();
    }

    public boolean isExit() {
        return false;
    }
}