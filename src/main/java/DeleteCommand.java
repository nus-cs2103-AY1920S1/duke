class DeleteCommand extends Command {

    private int deletedItemNo;

    DeleteCommand(int deletedItemNo) {
        super(false);
        this.deletedItemNo = deletedItemNo;
    }

    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.delete(deletedItemNo, ui);
    }
}
