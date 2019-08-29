package weijie.duke.commands;

import weijie.duke.exceptions.DukeInvalidInputException;
import weijie.duke.models.Task;
import weijie.duke.repos.IRepository;
import weijie.duke.responses.TaskResponse;

import java.util.Collections;

public class DeleteCommand implements ITaskCommand {
    private IRepository<Task> repo;

    public DeleteCommand(IRepository<Task> repo) {
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

        Task deletedTask = repo.get(id);
        repo.delete(id);
        int size = repo.getSize();

        String responseFormat = "Noted. I've removed this task:\n  " + deletedTask.getDescription()
                + "\nNow you have " + size + " tasks in the list.";
        return new TaskResponse(responseFormat, Collections.singletonList(deletedTask));
    }
}
