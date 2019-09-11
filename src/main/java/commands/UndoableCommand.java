package commands;

import java.io.IOException;
import storage.Storage;
import util.TaskList;
import ui.Ui;

/**
 * Encapsulates a user command that can be undo-ed.
 *
 * <p> Abstract class whose undo method is to be implemented by
 * child classes. </p>
 *
 * @author atharvjoshi
 * @version CS2103 AY19/20 Sem 1 iP Week 5
 */
public abstract class UndoableCommand extends Command {
    /**
     * Instantiates an undoable command.
     *
     * @param imperative the term used to identify the command type.
     */
    UndoableCommand(String imperative) {
        super(imperative);
    }

    /** Abstract execute method to be implemented by child classes. */
    public abstract String undo(TaskList tasks, Ui ui, Storage storage) throws IOException;
}
