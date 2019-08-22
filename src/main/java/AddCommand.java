import java.time.format.DateTimeFormatter;
import java.util.List;

abstract class AddCommand implements Command {
    protected final String command;
    protected final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    public AddCommand(String command) {
        this.command = command;
    }

    abstract protected Task instantiateTask();

    public void execute(List<Task> tasks, Ui ui, Storage storage) {
        final Task t = instantiateTask();
        tasks.add(t);

        storage.save(tasks);
        ui.displaySuccessfullyAddedTask("Got it. I've added this task: ", t, tasks.size());

    }
}
