package duke.command.list;

import duke.command.Command;
import duke.command.CommandProducer;
import duke.task.TasksController;
import error.command.CommandCreationException;

public class ListCommandProducer extends CommandProducer {
    private TasksController tasksController;

    public ListCommandProducer(TasksController tasksController) {
        super("list");
        this.tasksController = tasksController;
    }

    @Override
    public Command generateCommand(String arguments) throws CommandCreationException {
        if (!arguments.equals("")) {
            throw new CommandCreationException("List does not accept arguments.");
        }

        return new ListCommand(tasksController);
    }
}
