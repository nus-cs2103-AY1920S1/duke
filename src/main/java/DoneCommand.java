class DoneCommand extends Command {

    private int itemNo;

    DoneCommand(int itemNo) {
        super(false);
        this.itemNo = itemNo;
    }

    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.done(itemNo, ui);
    }
}
