import java.io.IOException;

public class DoneCommand extends Command {

    private int entry;

    public DoneCommand(int entry) {
        this.entry = entry;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        tasks.markAsDone(entry);
        ui.showDone(tasks.getTask(entry));
        try {
            storage.writeToFile(tasks);
        } catch (IOException ex) {
            throw new DukeException("☹ OOPS!!! I cannot read your file! :(");
        }
    }

}
