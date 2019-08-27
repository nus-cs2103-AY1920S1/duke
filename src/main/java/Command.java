public abstract class Command {
    String fullCommand;
    String[] splitInput;

    Command(String fullCommand) {
        this.fullCommand = fullCommand;
        splitInput = fullCommand.split(" ");
    }
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
