public class DoneCommand extends Command {
    public DoneCommand() {
        this("");
    }

    public DoneCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }
    
    public Command clone(String fullCommand) {
        return new DoneCommand(fullCommand);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int itemId = Parser.parseDone(this.fullCommand);
        tasks.markAsDone(itemId);
        ui.printResponse("Nice! I've marked this task as done: \n  "
                + tasks.get(itemId).toString());
    }

    public boolean isExit() {
        return false;
    }
}