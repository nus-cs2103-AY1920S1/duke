package duke.command.command;

import duke.command.Command;
import duke.command.CommandType;
import duke.command.UndoAction;
import duke.command.entities.TaskSorts;
import duke.task.Task;
import duke.task.TasksController;
import error.command.CommandCreationException;
import error.storage.StorageException;
import error.ui.UiException;
import ui.UiController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SortCommand extends Command {
    private static final String INVALID_ARGUMENT_MESSAGE = "☹ OOPS!!! Please enter a valid sort! :-(";
    private static final String STORAGE_ERROR_MESSAGE = "☹ OOPS!!! Unable to access storage file! :-(";

    TaskSorts comparator;
    List<Task> previousCopy;

    public SortCommand(String arguments, UiController ui, TasksController tasksController) throws CommandCreationException {
        super(CommandType.SORT, ui, tasksController);

        Optional<TaskSorts> taskSort = Arrays.stream(TaskSorts.values())
                .filter(sort -> sort.keyword.equals(arguments.toLowerCase()))
                .findFirst();

        if (taskSort.isEmpty()) {
            throw new CommandCreationException(INVALID_ARGUMENT_MESSAGE);
        }

        try {
            this.comparator = taskSort.get();
            this.previousCopy = new ArrayList<>();
            this.previousCopy.addAll(tasksController.getTasks());
        } catch (StorageException e) {
            throw new CommandCreationException(STORAGE_ERROR_MESSAGE);
        }
    }

    @Override
    public void execute() throws UiException {
        tasksController.sortTasks(comparator);
    }

    @Override
    public Optional<UndoAction> getUndoAction() {
        return Optional.of(() -> {
            tasksController.setNewTasksList(previousCopy, false);
            ui.displayOutput("Noted. I have reverted your list of tasks.");
        });
    }
}