public class DeleteCommand extends Command {
    public DeleteCommand() {
        this("");
    }

    public DeleteCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }
    
    public Command clone(String fullCommand) {
        return new DeleteCommand(fullCommand);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int itemId = Parser.parseDelete(this.fullCommand);
        Task item = tasks.remove(itemId);
        ui.printResponse("Noted. I've removed this task:  \n  "
                + item.toString() + "\n"
                + "Now you have " + tasks.size() +" tasks in the list.");
    }

    public boolean isExit() {
        return false;
    }
}