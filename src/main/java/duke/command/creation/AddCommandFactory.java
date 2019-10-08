package duke.command.creation;

import duke.command.Command;
import duke.command.add.AddCommand;
import duke.task.Task;
import duke.task.TasksController;
import duke.task.creation.TaskFactory;
import error.command.CommandCreationException;
import error.task.TaskCreationException;
import util.strings.CommandSplitter;

import java.util.Optional;

public class AddCommandFactory implements CommandFactory {
    private TaskFactory taskFactory;
    private TasksController tasksController;

    public AddCommandFactory(TasksController tasksController) {
        this.taskFactory = new TaskFactory();
        this.tasksController = tasksController;
    }

    @Override
    public Optional<Command> getCommandFromUserInput(String input) throws CommandCreationException {
        String keyword = CommandSplitter.getCommand(input);
        String arguments = CommandSplitter.getArguments(input);

        Optional<Task> task;

        try {
            task = this.taskFactory.getTask(keyword, arguments);
        }  catch (TaskCreationException e) {
            throw new CommandCreationException(e.getMessage());
        }

        if (task.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(new AddCommand(task.get(), tasksController));
    }
}
