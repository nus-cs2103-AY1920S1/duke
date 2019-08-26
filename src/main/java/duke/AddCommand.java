package duke;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(this.task);
        ui.printAddMessage(this.task, tasks);

        try {
            storage.writeToHardDisk(tasks);
        } catch (DukeException ex) {
            ui.printException(ex);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof AddCommand) {
            AddCommand addCommand = (AddCommand) o;
            return this.task.equals(addCommand.task);
        }
        return false;
    }
}
