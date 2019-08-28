public class ListCommand extends Command {

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("Here are the tasks in your list:");
        int index = 0;
        for (Task t : tasks.tasks) {
            System.out.println((index + 1) + "." + t.toString());
            index++;
        }
    }
}
