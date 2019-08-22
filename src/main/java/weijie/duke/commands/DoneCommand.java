package weijie.duke.commands;

import weijie.duke.models.Task;
import weijie.duke.repos.IRepository;
import weijie.duke.responses.TaskResponse;

import java.util.Collections;

public class DoneCommand implements ITaskCommand {
    private IRepository<Task> repo;

    public DoneCommand(IRepository<Task> repo) {
        this.repo = repo;
    }

    @Override
    public TaskResponse execute(String... args) {
        int id = Integer.parseInt(args[1]) - 1;
        Task task = repo.get(id);
        task.markAsDone();
        repo.update(id, task);

        String responseFormat = "Nice! I've marked this task as done:\n  " + task.getDescription();
        return new TaskResponse(responseFormat, Collections.singletonList(task));
    }
}
