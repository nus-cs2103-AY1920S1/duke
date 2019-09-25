package weijie.duke.commands;

import weijie.duke.exceptions.DukeInvalidInputException;
import weijie.duke.models.Deadline;
import weijie.duke.models.Task;
import weijie.duke.repos.IRepository;
import weijie.duke.responses.TaskResponse;
import weijie.duke.utils.DateUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class AddDeadlineCommand extends AddCommand {

    AddDeadlineCommand(IRepository<Task> repo) {
        super(repo);
    }

    @Override
    @SuppressWarnings("Duplicates")
    public TaskResponse executeAdd(String... args) {
        String input = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
        String[] descriptionAndDate = input.split(" /by ");

        if (descriptionAndDate.length <= 1) {
            return new TaskResponse(new DukeInvalidInputException("Specify date and time for deadline"));
        }

        LocalDateTime dateTime;
        try {
            dateTime = LocalDateTime.parse(descriptionAndDate[1].trim(), DateUtils.DUKE_DATETIME_PARSE_FORMAT);
        } catch (DateTimeParseException e) {
            return new TaskResponse(
                    new DukeInvalidInputException("Date and time must be in the format DD/MM/YYYY HHMM"));
        }

        Task task = new Deadline(descriptionAndDate[0], dateTime);

        return tryCreateTask(task);
    }
}
