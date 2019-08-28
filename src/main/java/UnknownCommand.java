public class UnknownCommand extends Command {

    UnknownCommand() {}

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
