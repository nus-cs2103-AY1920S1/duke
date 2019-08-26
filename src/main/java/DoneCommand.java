public class DoneCommand extends Command {
    int argument;

    public DoneCommand(int argument) {
        this.argument = argument;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task myTask = tasks.getTask(argument);
        myTask.markAsDone();
        ui.printMessage("Nice! I've marked this task as done:\n  " + myTask);
        storage.save(tasks);
    }
}
