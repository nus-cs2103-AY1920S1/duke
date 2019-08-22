package weijie.duke.commands;

import weijie.duke.models.Task;
import weijie.duke.repos.IRepository;
import weijie.duke.responses.TaskResponse;

public abstract class AddCommand implements ITaskCommand {
    IRepository<Task> repo;

    AddCommand(IRepository<Task> repo) {
        this.repo = repo;
    }

    @Override
    public abstract TaskResponse execute(String... args);

    String getResponseFormat() {
        int size = repo.getSize();
        return "Got it. I've added this task:\n  %s\nNow you have " + size + " tasks in the list.";
    }
}
