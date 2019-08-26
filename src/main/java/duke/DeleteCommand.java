package duke;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        super();
        this.index = index;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index > tasks.getListSize()) {
            throw new DukeException("OOPS!!! duke.Task not found.");
        }
        Task task = tasks.getTask(index);
        tasks.deleteTask(task);
        try {
            storage.writeToHardDisk(tasks);
        } catch (DukeException ex) {
            ui.printException(ex);
        }

        ui.printDeleteMessage(task, tasks);
}

    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof DeleteCommand) {
            DeleteCommand deleteCommand = (DeleteCommand) o;
            return this.index == deleteCommand.index;
        }
        return false;
    }
}
