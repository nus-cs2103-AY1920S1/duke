public class DeleteCommand extends Command {

    private String directive;
    private int position;

    public DeleteCommand(String directive, int position) {
        this.directive = directive;
        this.position = position;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.deleteTaskAtNumber(position);
        storage.writeListToFile(taskList);
    }
}
