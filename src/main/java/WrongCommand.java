public class WrongCommand extends Command {

    public WrongCommand(String input) {
        super(input);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Duke.print("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }



}

