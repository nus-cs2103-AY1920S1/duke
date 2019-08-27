public class PrintListCommand extends Command {
    public PrintListCommand() {
        super();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("     Here are the tasks in your list:");
        for (int i = 1; i < tasks.size() + 1; i++) {
            System.out.println(String.format("     %d. %s", i, tasks.get(i)));
        }
    }

    @Override
    public boolean isRunning() {
        return true;
    }
}
