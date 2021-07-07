package weijie.duke.commands;

import weijie.duke.exceptions.DukeInvalidInputException;
import weijie.duke.models.Task;
import weijie.duke.repos.IRepository;
import weijie.duke.responses.TaskResponse;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FindCommand implements ITaskCommand {

    private IRepository<Task> repo;

    FindCommand(IRepository<Task> repo) {
        this.repo = repo;
    }

    @Override
    public TaskResponse execute(String... args) {
        String searchParam = String.join(" ", Arrays.copyOfRange(args, 1, args.length));

        if (searchParam.isEmpty()) {
            return new TaskResponse(new DukeInvalidInputException("Specify a search term."));
        }

        List<Task> queriedTasks = repo.find(searchParam);

        if (queriedTasks.isEmpty()) {
            return new TaskResponse("No matching tasks found.", queriedTasks);
        }

        String responseFormat = "I found them all. I won. Tipped the cosmic scales to balance.\n"
                + IntStream.rangeClosed(1, queriedTasks.size())
                .mapToObj(index -> index + ".%s")
                .collect(Collectors.joining("\n"));

        return new TaskResponse(responseFormat, queriedTasks);
    }

    @Override
    public Optional<CommandState> getCommandState() {
        return Optional.empty();
    }
}
