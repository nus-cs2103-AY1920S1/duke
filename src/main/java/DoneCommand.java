public class DoneCommand extends Command {

    private int index;

    DoneCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task t = tasks.setIsDone(index);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(t);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(" ☹ OOPS!!! There is no task number " + (index + 1));
        }

        persistState(tasks, storage);
    }
}
