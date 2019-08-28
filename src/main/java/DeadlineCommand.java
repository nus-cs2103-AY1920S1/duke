import java.io.IOException;

public class DeadlineCommand extends Command {

    private Deadline deadlineToAdd;

    public DeadlineCommand(String description, String dateTime) throws DukeException {
        deadlineToAdd = new Deadline(description, dateTime);
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        tasks.addToList(deadlineToAdd);
        ui.operateTask(deadlineToAdd, tasks, true);
        try {
            storage.writeToFile(tasks);
        } catch (IOException ex) {
            throw new DukeException("☹ OOPS!!! I cannot read your file! :(");
        }
    }
}
