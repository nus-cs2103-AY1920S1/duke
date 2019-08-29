import java.time.LocalDateTime;

public class EventCommand extends Command {
    private String args;

    public EventCommand(String args) {
        this.args = args;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] splitStr = args.split("/at");
        if (splitStr.length == 1) {
            throw new DukeException("Invalid format. Please use '/at' to state your start and end dates");
        }
        String[] dateString = splitStr[1].trim().split(" - "); //e.g. 2/12/2019 1800 - 2/12/2019 1800
        LocalDateTime start = Parser.parseDateTime(dateString[0]);
        LocalDateTime end = Parser.parseDateTime(dateString[1]);

        Task task = new Events(false, splitStr[0].trim(), start, end);
        tasks.addTask(task);
        storage.updateFile(tasks);
    }
}
