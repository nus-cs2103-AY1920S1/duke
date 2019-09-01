public class ExitCommand extends Command {
    public ExitCommand() {
        this("");
    }

    public ExitCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }
    
    public Command clone(String fullCommand) {
        return new ExitCommand(fullCommand);
    }
    
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printResponse("Bye. Hope to see you again soon!");
    }

    public boolean isExit() {
        return true;
    }
}