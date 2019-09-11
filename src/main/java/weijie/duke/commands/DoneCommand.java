package weijie.duke.commands;

import weijie.duke.exceptions.DukeIoException;
import weijie.duke.exceptions.DukeInvalidInputException;
import weijie.duke.models.Task;
import weijie.duke.repos.IRepository;
import weijie.duke.responses.TaskResponse;

import java.util.Collections;
import java.util.Optional;

public class DoneCommand implements ITaskCommand {
    private IRepository<Task> repo;
    private Task updatedTask;
    private int updatedTaskId;

    public DoneCommand(IRepository<Task> repo) {
        this.repo = repo;
    }

    @Override
    @SuppressWarnings("Duplicates")
    public TaskResponse execute(String... args) {
        if (args.length < 2) {
            return new TaskResponse(
                    new DukeInvalidInputException("☹ OOPS!!! Please input the number of the task to mark as done."));
        }

        int id = Integer.parseInt(args[1]) - 1;

        if (id > repo.getSize()) {
            return new TaskResponse(
                    new DukeInvalidInputException("☹ OOPS!!! Task with that number does not exist!"));
        }

        Task task = repo.get(id);
        task.markAsDone();

        try {
            repo.update(id, task);
            updatedTask = task;
            updatedTaskId = id;
        } catch (DukeIoException e) {
            return new TaskResponse(e);
        }

        String responseFormat = "Nice! I've marked this task as done:\n  " + task.getDescription();
        return new TaskResponse(responseFormat, Collections.singletonList(task));
    }

    @Override
    public Optional<UndoingAction> getUndoingAction() {
        UndoingAction undoingAction = () -> {
            assert updatedTask != null;
            updatedTask.unmarkAsDone();

            try {
                repo.update(updatedTaskId, updatedTask);
                return new TaskResponse("Undid marking as done:\n  %s",
                        Collections.singletonList(updatedTask));

            } catch (DukeIoException e) {
                return new TaskResponse(e);
            }
        };

        return Optional.of(undoingAction);
    }
}
