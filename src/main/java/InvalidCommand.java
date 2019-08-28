public class InvalidCommand extends Command {
    public InvalidCommand() {
        super();
    }

    public void execute (TaskList task, Ui ui, Storage storage) throws DukeException {
            throw new InvalidCommandException("I'm sorry, but I don't know what that means :-(");
    }
}
