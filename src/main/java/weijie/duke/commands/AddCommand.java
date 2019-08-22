package weijie.duke.commands;

import weijie.duke.models.Task;
import weijie.duke.repos.IRepository;
import weijie.duke.responses.TaskResponse;

import java.util.Collections;

public class AddCommand implements ITaskCommand {
    private IRepository<Task> repo;

    public AddCommand(IRepository<Task> repo) {
        this.repo = repo;
    }

    @Override
    public TaskResponse execute(String... args) {
        String description = String.join(" ", args);
        Task task = new Task(description);
        repo.create(task);

        return new TaskResponse("added: %s", Collections.singletonList(task));
    }
}
