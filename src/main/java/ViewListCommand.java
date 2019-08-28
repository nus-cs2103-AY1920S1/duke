public class ViewListCommand extends Command {
    @Override
    public boolean executeCommand(TaskList taskList, Storage storage, Ui ui) {
        System.out.println(taskList.toString());
        return true;
    }
}
