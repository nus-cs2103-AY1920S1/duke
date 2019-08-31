import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddCommand extends Command {

    private String taskCmd;
    //Time
    final String timePattern = "d MMMM yyyy, h:mma";
    DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern(timePattern);

    public AddCommand(String taskCmd) {
        super();
        this.taskCmd = taskCmd;
    }

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) throws DukeException {
        String taskName;
        String[] userWords;
        switch (taskCmd) {
        case "todo":
            taskName = ui.readDesc().trim();
            if (taskName.isEmpty()) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            tasks.addTask(new Task(taskName));
            break;
        case "deadline":
            taskName = ui.readDesc().trim();
            if (taskName.isEmpty()) {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            userWords = taskName.split("/by");
            if (userWords.length == 1) {
                throw new DukeException("☹ OOPS!!! The date/time of a deadline cannot be empty or is wrongly typed.");
            }
            try {
                LocalDateTime dateObj = LocalDateTime.parse(userWords[1].trim(),
                        DateTimeFormatter.ofPattern("d/MM/yyyy Hmm"));
                String date = dateObj.format(dateTimeFormat);
                tasks.addTask(new Deadline(userWords[0].trim(), date));
            } catch (DateTimeParseException e) {
                throw new DukeException("Could not recognise date and time. Please follow the format: dd/mm/yyyy HHmm");
            }
            break;
        case "event":
            taskName = ui.readDesc().trim();
            if (taskName.isEmpty()) {
                throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
            }
            userWords = taskName.split("/at");
            if (userWords.length == 1) {
                throw new DukeException("☹ OOPS!!! The date/time of an event cannot be empty or is wrongly typed.");
            }
            tasks.addTask(new Event(userWords[0].trim(), userWords[1].trim()));
            break;
        default:
            throw new DukeException("Wrong type of command entered");
        }
    }
}
