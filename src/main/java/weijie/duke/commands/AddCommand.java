package weijie.duke.commands;

import weijie.duke.exceptions.DukeIoException;
import weijie.duke.models.Task;
import weijie.duke.repos.IRepository;
import weijie.duke.responses.TaskResponse;

import java.util.Collections;
import java.util.Optional;

public abstract class AddCommand implements ITaskCommand {
    IRepository<Task> repo;
    private Task createdTask;

    AddCommand(IRepository<Task> repo) {
        this.repo = repo;
    }

    @Override
    public Optional<UndoingAction> getUndoingAction() {
        UndoingAction undoingAction = () -> {
            assert createdTask != null;

            try {
                repo.delete(createdTask);
                return new TaskResponse(getUndoResponseFormat(), Collections.singletonList(createdTask));

            } catch (DukeIoException e) {
                return new TaskResponse(e);
            }
        };

        return Optional.of(undoingAction);
    }

    TaskResponse tryCreateTask(Task task) {
        try {
            repo.create(task);
            createdTask = task;
            return new TaskResponse(getResponseFormat(), Collections.singletonList(task));

        } catch (DukeIoException e) {
            return new TaskResponse(e);
        }
    }

    private String getResponseFormat() {
        int size = repo.getSize();
        return "Got it. I've added this task:\n  %s\nNow you have " + size + " tasks in the list.";
    }

    private String getUndoResponseFormat() {
        return "Undid previous command.\nThis task was removed:\n  %s";
    }
}
