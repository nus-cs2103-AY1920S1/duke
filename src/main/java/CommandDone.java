public class CommandDone extends Command {

    private int position;

    public CommandDone (int i) {
        position = i;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.done(position);
        String curr = tasks.getList().get(position - 1).toString();
        storage.save(tasks.getList(), tasks.getNoOfTasks());
        ui.printString("Nice! I've marked this task as done:");
        ui.printString(curr);
        storage.save(tasks.getList(), tasks.getNoOfTasks());
    }
}
