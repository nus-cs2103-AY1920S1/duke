package duke;

public class ErrorCommand extends Command {
    ErrorCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public String execute(TaskList tasks) throws DukeException {
        String output = " ☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        return output;
    }
}
