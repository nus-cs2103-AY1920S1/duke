package duke;

public class DoneCommand extends Command {
    private int index;

    public DoneCommand(int index) {
        super();
        this.index = index;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index > tasks.getListSize()) {
            throw new DukeException("OOPS!!! duke.Task not found.");
        }
        Task task = tasks.getTask(index);
        task.markDone();
        try {
            storage.writeToHardDisk(tasks);
        } catch (DukeException ex) {
            ui.printException(ex);
        }

        ui.printDoneMessage(task);
    }

    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof DoneCommand) {
            DoneCommand doneCommand = (DoneCommand) o;
            return this.index == doneCommand.index;
        }
        return false;
    }
}
