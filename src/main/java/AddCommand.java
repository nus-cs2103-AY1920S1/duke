public class AddCommand extends Command {

    Task t;

    AddCommand(Task t) {
        this.t = t;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(t);

        System.out.println("Got it. I've added this task:");
        System.out.println(t);
        if (tasks.size() == 1) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        }

        persistState(tasks, storage);
    }
}
