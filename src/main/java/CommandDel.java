//package mypackage;

public class CommandDel extends Command {

    private int position;

    public CommandDel (int i) {
        position = i;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String curr = tasks.getList().get(position - 1).toString();
        tasks.delete(position);
        storage.save(tasks.getList(), tasks.getNoOfTasks());
        ui.printString("Noted. I've removed this task:");
        ui.printString(curr);
        ui.printString("Now you have " + tasks.getNoOfTasks() + " tasks in the list.");
        storage.save(tasks.getList(), tasks.getNoOfTasks());
    }
}
