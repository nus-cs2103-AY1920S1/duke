public class IllegalCommand extends Command {

    public IllegalCommand(String command) {
        super(command);
    }

    @Override
    public void execute(Sheet sh, Ui ui, Storage stor) throws DukeException{
        throw new IllegalCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
