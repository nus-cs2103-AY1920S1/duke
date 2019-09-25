package weijie.duke.commands;

import weijie.duke.exceptions.DukeIoException;
import weijie.duke.models.Task;
import weijie.duke.repos.IRepository;
import weijie.duke.responses.TaskResponse;

import java.util.Collections;
import java.util.Optional;

public abstract class AddCommand implements ITaskCommand {
    IRepository<Task> repo;
    private String[] args;
    private Task createdTask;

    AddCommand(IRepository<Task> repo) {
        this.repo = repo;
    }

    @Override
    public final TaskResponse execute(String... args) {
        this.args = args;
        return executeAdd(args);
    }

    public abstract TaskResponse executeAdd(String... args);

    @Override
    public Optional<CommandState> getCommandState() {
        CommandState commandState = new CommandState() {
            @Override
            public TaskResponse undo() {
                assert createdTask != null;

                try {
                    repo.delete(createdTask);
                    return new TaskResponse(getUndoResponseFormat(), Collections.singletonList(createdTask));

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
        return "I hope they remember you:\n  %s\nNow you have " + size + " tasks in the list.";
    }

    private String getUndoResponseFormat() {
        return "The hardest choices require the strongest wills."
                + "\nThis task was removed:\n  %s";
    }
}
