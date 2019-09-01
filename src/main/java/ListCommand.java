public class ListCommand extends Command {
    public ListCommand() {
        this("");
    }

    public ListCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }
    
    public Command clone(String fullCommand) {
        return new ListCommand(fullCommand);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printResponse("Here are the tasks in your list:\n" 
                + tasks.toString());
    }

    public boolean isExit() {
        return false;
    }
}