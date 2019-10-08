package duke.command.sort;

import duke.command.Command;
import duke.command.UndoAction;
import duke.task.Task;
import duke.task.TasksController;
import error.command.CommandCreationException;
import error.command.CommandNotExecutedException;
import error.ui.UiException;

import java.util.List;
import java.util.Optional;

public class SortCommand implements Command {

    private TaskSorts sortingMethod;
    private TasksController tasksController;

    private List<Task> oldCopy;
    private boolean isExecuted;

    public SortCommand(TaskSorts sort, TasksController tasksController) throws CommandCreationException {
        this.sortingMethod = sort;
        this.tasksController = tasksController;
    }

    @Override
    public void execute() throws UiException {
        this.oldCopy = tasksController.sortTasks(sortingMethod);
        this.isExecuted = true;
    }

    @Override
    public Optional<UndoAction> getUndoAction() throws CommandNotExecutedException {
        if (!this.isExecuted) {
            throw new CommandNotExecutedException();
        }

        if (this.oldCopy != null) {
            return Optional.of(() -> tasksController.setNewTasks(oldCopy, true));
        }

        return Optional.empty();
    }
}
