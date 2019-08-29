public class ExitCommand extends Command {
    public ExitCommand(String input){
        super(input);
    }
    public void execute(TaskList tasks, Ui ui, Storage storage){
        Duke.print("Bye. Hope to see you again soon!");
        this.isExit = true;
    }


}
