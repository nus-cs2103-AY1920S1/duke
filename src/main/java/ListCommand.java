class ListCommand extends Command {

    ListCommand() {
        super(false);
    }

    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showList(taskList);
    }
}
