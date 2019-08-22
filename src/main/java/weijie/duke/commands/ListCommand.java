package weijie.duke.commands;

import weijie.duke.models.Task;
import weijie.duke.repos.IRepository;
import weijie.duke.responses.TaskResponse;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ListCommand implements ITaskCommand {
    private IRepository<Task> repo;

    public ListCommand(IRepository<Task> repo) {
        this.repo = repo;
    }

    @Override
    public TaskResponse execute(String... args) {
        List<Task> tasks = repo.getAll();
        String responseFormat = "Here are the tasks in your list:\n"
                + IntStream.rangeClosed(1, tasks.size())
                .mapToObj(index -> index + ".%s")
                .collect(Collectors.joining("\n"));
        return new TaskResponse(responseFormat, tasks);
    }
}
