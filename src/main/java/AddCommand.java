public class AddCommand extends Command {
    AddCommand(String details) {
        super(details);
    }

    @Override
    void execute(TaskList tasks, TextUi ui, Storage storage) throws
            DukeException {
        int numberOfTasks = tasks.size();
        ui.showText("Got it. I've added this task:"
                + "\n  " + tasks.get(numberOfTasks - 1)
                + "\nNow you have " + numberOfTasks + " tasks in the list.");
        save(tasks, storage);
    }
}
