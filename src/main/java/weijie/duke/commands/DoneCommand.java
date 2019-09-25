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
    private String[] args;
    private Task updatedTask;
    private int updatedTaskId;

    public DoneCommand(IRepository<Task> repo) {
        this.repo = repo;
    }

    @Override
    @SuppressWarnings("Duplicates")
    public TaskResponse execute(String... args) {
        this.args = args;

        if (args.length < 2) {
            return new TaskResponse(
                    new DukeInvalidInputException("Input the number of the task to mark as done."));
        }

        int id = Integer.parseInt(args[1]) - 1;

        if (id >= repo.getSize()) {
            return new TaskResponse(
                    new DukeInvalidInputException("Task with that number does not exist."));
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

        String responseFormat = "The work is done. It always will be.\n  " + task.getDescription();
        return new TaskResponse(responseFormat, Collections.singletonList(task));
    }

    @Override
    public Optional<CommandState> getCommandState() {
        CommandState commandState = new CommandState() {
            @Override
            public TaskResponse undo() {
                assert updatedTask != null;
                updatedTask.unmarkAsDone();

                try {
                    repo.update(updatedTaskId, updatedTask);
                    return new TaskResponse("When I'm done, half of humanity will still exist:\n  %s",
                            Collections.singletonList(updatedTask));

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
