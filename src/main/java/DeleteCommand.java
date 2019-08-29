public class DeleteCommand extends Command {

    private int index;

    DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {

        Task t = tasks.removeTask(index);
        System.out.println("Noted. I've removed this task:");
        System.out.println(t);
        if (tasks.getSize() == 1) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
        }

        persistState(tasks, storage);
    }

    private void delete(int index) throws DukeException {

    }


}
