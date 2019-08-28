class DeleteCommand extends Command {
    protected int deletedTask;

    DeleteCommand(int deleted) {
        super();
        this.deletedTask = deleted;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.printRemoveMessage(tasks.delete(this.deletedTask), tasks.size());
    }
}