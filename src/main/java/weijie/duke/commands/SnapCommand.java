package weijie.duke.commands;

import weijie.duke.exceptions.DukeIoException;
import weijie.duke.models.Task;
import weijie.duke.repos.IRepository;
import weijie.duke.responses.TaskResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SnapCommand implements ITaskCommand {
    private IRepository<Task> repo;
    private List<Task> snappedTasks;

    public SnapCommand(IRepository<Task> repo) {
        this.repo = repo;
        this.snappedTasks = new ArrayList<>();
    }

    @Override
    public TaskResponse execute(String... args) {

        int size = repo.getSize();
        for (int i = size; i > (size+ 1) / 2; i--) {
            int toBeSnapped = (int) (Math.random() * i);
            snappedTasks.add(repo.get(toBeSnapped));

            try {
                repo.delete(toBeSnapped);
            } catch (DukeIoException e) {
                return new TaskResponse(e);
            }
        }

        String responseFormat = "Perfectly balanced. As all things should be.\nTasks that have ceased to exist:\n"
                + IntStream.rangeClosed(1, snappedTasks.size())
                .mapToObj(index -> index + ". %s")
                .collect(Collectors.joining("\n"));

        return new TaskResponse(responseFormat, snappedTasks);
    }

    @Override
    public Optional<CommandState> getCommandState() {
        CommandState commandState = new CommandState() {
            @Override
            public TaskResponse undo() {
                assert snappedTasks != null;

                for (Task snappedTask : snappedTasks) {
                    try {
                        repo.create(snappedTask);
                    } catch (DukeIoException e) {
                        return new TaskResponse(e);
                    }
                }

                String responseFormat = "You could not live with your own failure. Where did that bring you? "
                        + "Back to me.\nThese tasks have returned:\n"
                        + IntStream.rangeClosed(1, snappedTasks.size())
                        .mapToObj(index -> index + ". %s")
                        .collect(Collectors.joining("\n"));

                return new TaskResponse(responseFormat, snappedTasks);
            }

            @Override
            public TaskResponse redo() {
                return execute();
            }
        };

        return Optional.of(commandState);
    }
}
