package duke.command;

import duke.model.Task;
import duke.storage.Storage;
import duke.ui.Ui;

import java.time.format.DateTimeFormatter;
import java.util.List;

abstract class AddCommand implements Command {
    protected static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    protected final String command;
    private final Task task;

    public AddCommand(String command) {
        this.command = command;
        task = instantiateTask();
        assert task != null;
    }

    /**
     * Parses the given command to produce a Task instance with all the relevant properties filled in.
     *
     * @return The Task instance to add with data from the command.
     */
    protected abstract Task instantiateTask();

    @Override
    public void execute(List<Task> tasks, Ui ui, Storage storage) {
        tasks.add(task);

        storage.save(tasks);
        ui.displaySuccessfullyAddedTask("Got it. I've added this task: ", task, tasks.size());

    }
}
