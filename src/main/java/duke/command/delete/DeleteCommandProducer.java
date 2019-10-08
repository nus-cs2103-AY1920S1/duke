package duke.command.delete;

import duke.command.Command;
import duke.command.CommandProducer;
import duke.task.TasksController;
import error.command.CommandCreationException;

public class DeleteCommandProducer extends CommandProducer {
    private TasksController tasksController;

    public DeleteCommandProducer(TasksController tasksController) {
        super("delete");
        this.tasksController = tasksController;
    }

    @Override
    public Command generateCommand(String arguments) throws CommandCreationException {
        int index = 0;
        boolean deleteAll = false;


        try {
            index = Integer.parseInt(arguments) - 1;
        } catch (NumberFormatException e) {
            if (arguments.equals("all")) {
                deleteAll = true;
            } else {
                throw new CommandCreationException("Herh herh herh. Invalid command that is..");
            }
        }

        return new DeleteCommand(index, deleteAll, tasksController);
    }
}
