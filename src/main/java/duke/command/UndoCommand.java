package duke.command;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

public class UndoCommand extends Command {
    private int stepsToUndo;

    public UndoCommand(int stepsToUndo) {
        this.stepsToUndo = stepsToUndo;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (stepsToUndo > tasks.getMaxUndo() || stepsToUndo <= 0) {
            throw new DukeException("Number of undo operations cannot exceed total number of operations performed "
                    + "and cannot be less than or equal to 0!");
        }

        tasks.undo(stepsToUndo);
        ui.printUndoSuccessMessage(stepsToUndo);
        storage.save(tasks.getAllTasks());
    }
}
