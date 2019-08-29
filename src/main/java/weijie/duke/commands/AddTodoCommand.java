package weijie.duke.commands;

import weijie.duke.exceptions.DukeException;
import weijie.duke.exceptions.DukeIOException;
import weijie.duke.exceptions.DukeInvalidInputException;
import weijie.duke.models.Task;
import weijie.duke.models.Todo;
import weijie.duke.repos.IRepository;
import weijie.duke.responses.TaskResponse;

import java.util.Arrays;
import java.util.Collections;

public class AddTodoCommand extends AddCommand {

    AddTodoCommand(IRepository<Task> repo) {
        super(repo);
    }

    @Override
    @SuppressWarnings("Duplicates")
    public TaskResponse execute(String... args) {
        String description = String.join(" ", Arrays.copyOfRange(args, 1, args.length));

        if (description.isEmpty()) {
            return new TaskResponse(new DukeInvalidInputException("☹ OOPS!!! The description of a todo cannot be empty."));
        }

        Task task = new Todo(description);
        try {
            repo.create(task);
        } catch (DukeIOException e) {
            return new TaskResponse(e);
        }

        return new TaskResponse(getResponseFormat(), Collections.singletonList(task));
    }
}
