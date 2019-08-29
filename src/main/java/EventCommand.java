import java.time.LocalDateTime;

public class EventCommand extends Command {
    private String args;

    public EventCommand(String args) {
        this.args = args;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] splitStr = args.split("/at");
        String[] dateString = splitStr[1].trim().split(" - "); //e.g. 2/12/2019 1800 - 2/12/2019 1800
        if (splitStr.length == 1) {
            throw new DukeException("Invalid format. Please include '/at' to state your start and end dates. " +
                    "\nE.g. event meeting /at 12/7/2019 2000 - 18/12/2019 1800");
        } else if (dateString.length != 2) {
            throw new DukeException("Invalid Format. Please enter both the start and end dates" +
                    "\nE.g. event meeting /at 12/7/2019 2000 - 18/12/2019 1800");
        } else if (splitStr[0].trim().isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty");
        }

        LocalDateTime start = Parser.parseDateTime(dateString[0]);
        LocalDateTime end = Parser.parseDateTime(dateString[1]);

        if (start.isAfter(end)) {
            throw new DukeException("☹ OOPS!!! Start DateTime cannnot be after End DateTime!");
        }
        Task task = new Events(false, splitStr[0].trim(), start, end);
        tasks.addTask(task);
        storage.updateFile(tasks);
    }
}
