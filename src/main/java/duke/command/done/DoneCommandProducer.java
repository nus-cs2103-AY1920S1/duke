package duke.command.done;

import duke.command.Command;
import duke.command.CommandProducer;
import duke.task.TasksController;
import error.command.CommandCreationException;

public class DoneCommandProducer extends CommandProducer {
    private TasksController tasksController;

    public DoneCommandProducer(TasksController tasksController) {
        super("done");
        this.tasksController = tasksController;
    }

    @Override
    public Command generateCommand(String arguments) throws CommandCreationException {
        int index;

        try {
            index = Integer.parseInt(arguments) - 1;
        } catch (NumberFormatException e) {
            throw new CommandCreationException("Please enter a numerical argument.");
        }

        return new DoneCommand(index, tasksController);
    }
}
