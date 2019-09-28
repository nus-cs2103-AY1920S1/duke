package duke.command.sort;

import duke.command.Command;
import duke.command.CommandProducer;
import duke.task.TasksController;
import error.command.CommandCreationException;

import java.util.Arrays;

public class SortCommandProducer extends CommandProducer {
    private TasksController tasksController;

    public SortCommandProducer(TasksController tasksController) {
        super("sort");
        this.tasksController = tasksController;
    }

    @Override
    public Command generateCommand(String arguments) throws CommandCreationException {
        TaskSorts sortingMethod = Arrays.stream(TaskSorts.values())
                .filter(sort -> sort.keyword.equals(arguments))
                .findFirst()
                .orElseThrow(() -> new CommandCreationException("Invalid argument, I sense."));

        return new SortCommand(sortingMethod, this.tasksController);
    }
}
