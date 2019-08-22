package weijie.duke.commands;

import weijie.duke.exceptions.DukeException;
import weijie.duke.models.Deadline;
import weijie.duke.models.Task;
import weijie.duke.repos.IRepository;
import weijie.duke.responses.TaskResponse;

import java.util.Arrays;
import java.util.Collections;

public class AddDeadlineCommand extends AddCommand {

    public AddDeadlineCommand(IRepository<Task> repo) {
        super(repo);
    }

    @Override
    public TaskResponse execute(String... args) {
        String input = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
        String[] descriptionAndDate = input.split(" /by ");

        if (descriptionAndDate.length <= 1) {
            return new TaskResponse(new DukeException("☹ OOPS!!! Must specify date/time for deadline"));
        }

        Task task = new Deadline(descriptionAndDate[0], descriptionAndDate[1]);
        repo.create(task);

        return new TaskResponse(getResponseFormat(), Collections.singletonList(task));
    }
}
