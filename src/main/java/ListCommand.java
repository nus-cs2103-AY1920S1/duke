public class ListCommand extends Command {

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("Here are the tasks in your list:");
        ui.showTasks(tasks);
        return false;
    }

}
