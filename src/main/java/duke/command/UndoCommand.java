package duke.command;

import duke.command.CommandEnum.Commands;
import duke.task.Task;
import duke.TaskList;
import duke.ui.DukeException;

public class UndoCommand extends Command {

    public UndoCommand() {
        super.carryOn = true;
    }

    private String undo() {
        return "Nice! Undone successful.";
    }

    private void undoCommand(Commands lastCommand, int size, TaskList taskList) {
        switch (lastCommand) {
        case ADD: //undo = delete
            new DeleteCommand(size - 1).execute(taskList);
            break;
        case DELETE: //undo = add back
            Task deletedTask = TaskList.getDeletedTask();
            new AddCommand(deletedTask).execute(taskList);
            break;
        case DONE: //undo = undone
            Task doneTask = taskList.getDoneTask();
            doneTask.undoMarkDone();
            break;
        }
    }

    @Override
    public String execute(TaskList taskList) {
        try {
            Commands lastCommand = taskList.getCommand();
            int size = taskList.getSize();
            undoCommand(lastCommand, size, taskList);
            return undo();
        } catch (IndexOutOfBoundsException ex) {
            return (new DukeException("The command cannot be undone.")).toString();
        }
    }
}
