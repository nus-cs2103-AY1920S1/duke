class ExitCommand extends Command {

    ExitCommand() {
        super(true);
    }

    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.showBye();
        taskList.writeTo(storage);
    }
}
