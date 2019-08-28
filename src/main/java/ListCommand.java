class ListCommand extends Command {
    ListCommand() {
        super();
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.printTaskList(tasks.getAllTasks());
    }
}