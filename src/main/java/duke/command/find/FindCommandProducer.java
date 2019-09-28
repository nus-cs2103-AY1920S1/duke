package duke.command.find;

import duke.command.Command;
import duke.command.CommandProducer;
import duke.task.TasksController;
import error.command.CommandCreationException;

public class FindCommandProducer extends CommandProducer {
    private TasksController tasksController;

    public FindCommandProducer(TasksController tasksController) {
        super("find");
        this.tasksController = tasksController;
    }

    @Override
    public Command generateCommand(String arguments) throws CommandCreationException {
        if (arguments.equals("")) {
            throw new CommandCreationException("Search parameter, I must have.");
        }

        return new FindCommand(arguments, this.tasksController);
    }
}
