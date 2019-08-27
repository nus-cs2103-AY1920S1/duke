public class DisplayListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("Here are the tasks in your list:");
        tasks.displayFullList();
    }
}

