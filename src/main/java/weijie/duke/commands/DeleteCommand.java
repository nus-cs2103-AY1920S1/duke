package weijie.duke.commands;

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
    public TaskResponse execute(String... args) {
        int id = Integer.parseInt(args[1]) - 1;
        Task deletedTask = repo.get(id);
        repo.delete(id);
        int size = repo.getSize();

        String responseFormat = "Noted. I've removed this task:\n  " + deletedTask.getDescription()
                + "\nNow you have " + size + " tasks in the list.";
        return new TaskResponse(responseFormat, Collections.singletonList(deletedTask));
    }
}
