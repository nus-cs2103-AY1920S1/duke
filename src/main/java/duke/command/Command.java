package duke.command;

import duke.component.TaskList;
import duke.component.Storage;
import duke.exception.DukeException;

public abstract class Command {
    private String command;

    protected Command(String command) {
        this.command = command;
    }

    /**
     *  Executes this command with the supplied <code>TaskList</code> and <code>Storage</code> objects.
     *  @param tasks associated <code>TaskList</code> object to execute the command with.
     *  @param fileMgr associated <code>Storage</code> object to execute the command with.
     *  @return a <code>String</code> containing the output of executing this command.
     *  @throws DukeException if an error occured during execution of this command.
     */
    public abstract String execute(TaskList tasks, Storage fileMgr) throws DukeException;
    
    /**
     *  Returns the raw command string associated with this <code>Command</code> object.
     *  @return the <code>String</code> that generated this <code>Command</code> object when parsed.
     */
    public String getCommand() {
        return this.command;
    }
    
    /**
     *  Returns <code>true</code> if executing this command terminates the associated <code>Duke</code> task maanger.
     *  @return <code>true</code> if executing this command terminates the associated <code>Duke</code> object.
     */
    public boolean willTerminate() {
        return false;
    }
}
