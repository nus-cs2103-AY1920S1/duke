package weijie.duke.commands;

import weijie.duke.exceptions.DukeInvalidInputException;
import weijie.duke.exceptions.DukeIoException;
import weijie.duke.models.Task;
import weijie.duke.repos.IRepository;
import weijie.duke.responses.TaskResponse;

import java.util.Collections;
import java.util.Optional;

public class DeleteCommand implements ITaskCommand {
    private IRepository<Task> repo;
    private String[] args;
    private Task deletedTask;
    private int deletedTaskIndex;

    public DeleteCommand(IRepository<Task> repo) {
        this.repo = repo;
    }

    @Override
    @SuppressWarnings("Duplicates")
    public TaskResponse execute(String... args) {
        this.args = args;

        if (args.length < 2) {
            return new TaskResponse(
                    new DukeInvalidInputException("Input the number of the task to delete."));
        }

        int id = Integer.parseInt(args[1]) - 1;

        if (id >= repo.getSize()) {
            return new TaskResponse(
                    new DukeInvalidInputException("A task with that number does not exist."));
        }

        Task toBeDeleted = repo.get(id);
        try {
            repo.delete(id);
            deletedTask = toBeDeleted;
            deletedTaskIndex = id;
        } catch (DukeIoException e) {
            return new TaskResponse(e);
        }

        int size = repo.getSize();

        String responseFormat = "Gone. Reduced to atoms:\n  " + toBeDeleted.getDescription()
                + "\nNow you have " + size + " tasks in the list.";
        return new TaskResponse(responseFormat, Collections.singletonList(toBeDeleted));
    }

    @Override
    public Optional<CommandState> getCommandState() {
        CommandState commandState = new CommandState() {
            @Override
            public TaskResponse undo() {
                assert deletedTask != null;

                try {
                    repo.insert(deletedTaskIndex, deletedTask);
                    return new TaskResponse("Now is no time at all.\nThis task was restored:\n  %s",
                            Collections.singletonList(deletedTask));

                } catch (DukeIoException e) {
                    return new TaskResponse(e);
                }
            }

            @Override
            public TaskResponse redo() {
                return execute(args);
            }
        };

        return Optional.of(commandState);
    }
}
