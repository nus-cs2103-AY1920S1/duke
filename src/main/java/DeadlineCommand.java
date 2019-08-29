import java.time.LocalDateTime;

public class DeadlineCommand extends Command {
    private String args;

    public DeadlineCommand(String args) {
        this.args = args;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] splitStr = args.split("/by");
        if (splitStr.length == 1) {
            throw new DukeException("Invalid format. Please include '/by' to state your deadline"
                    + "\nE.g. deadline work /by 12/7/2019 2000");
        } else if (splitStr[0].trim().isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty");
        }
        LocalDateTime deadline = Parser.parseDateTime(splitStr[1].trim());
        Task task = new Deadlines(false, splitStr[0].trim(), deadline);
        tasks.addTask(task);
        storage.updateFile(tasks);
    }
}
