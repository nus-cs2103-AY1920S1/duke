package duke.command;

import duke.component.TaskList;
import duke.component.Storage;

/**
 * An executable object representation of a user command to list all current tracked tasks.
 */
public class ListCommand extends Command {
    /**
     * Constructs a <code>ListCommand</code> object.
     */
    public ListCommand() {
        super("list");
    }
    
    /**
     * Executes this command with the supplied <code>TaskList</code> and <code>Storage</code> objects.
     * 
     * @param tasks associated <code>TaskList</code> object to execute the command with.
     * @param fileMgr associated <code>Storage</code> object to execute the command with.
     * @return a <code>String</code> containing the output of executing this command.
     */
    public String execute(TaskList tasks, Storage fileMgr) {
        return tasks.toString();
    }
}
