package weijie.duke.commands;

import weijie.duke.exceptions.DukeInvalidInputException;
import weijie.duke.models.Task;
import weijie.duke.models.Todo;
import weijie.duke.repos.IRepository;
import weijie.duke.responses.TaskResponse;

import java.util.Arrays;

public class AddTodoCommand extends AddCommand {

    AddTodoCommand(IRepository<Task> repo) {
        super(repo);
    }

    @Override
    @SuppressWarnings("Duplicates")
    public TaskResponse executeAdd(String... args) {
        String description = String.join(" ", Arrays.copyOfRange(args, 1, args.length));

        if (description.isEmpty()) {
            return new TaskResponse(
                    new DukeInvalidInputException("The description of a todo cannot be empty."));
        }

        Task task = new Todo(description);

        return tryCreateTask(task);
    }
}
