public class UpdateCommand extends Command {

    private String directive;
    private int position;

    public UpdateCommand(String directive, int position) {
        super();
        this.directive = directive;
        this.position = position;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.markNumberedTaskAsDone(position);
        storage.writeListToFile(taskList);

    }
}
