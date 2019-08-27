public class AddCommand extends Command {
    Task task;

    public AddCommand(Task task) {
        super();
        this.task = task;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(this.task);
        System.out.println("     Got it. I've added this task: ");
        System.out.println("       " + this.task);
        System.out.println(String.format("     Now you have %d tasks in the list.", tasks.size()));
    }

    @Override
    public boolean isRunning() {
        return true;
    }
}
