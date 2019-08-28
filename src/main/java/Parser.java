/**
 * This is a class which makes sense and differentiates the commands.
 * @author Choong Yong Xin
 */

public class Parser {
    private String command;

    public Parser(String command) {
        this.command = command;
    }

    //Makes sense of user input to which command it corresponds to
    static Command parse(String command) throws DukeException {
        if (command.equals("bye")){
            return new ByeCommand(command);
        } else if (command.equals("list")) {
            return new ListCommand(command);
        } else if (command.length() >= 4 && command.substring(0, 4).equals("done")) {
            return new DoneCommand(command);
        } else if (command.length() >= 4 && command.substring(0, 4).equals("todo")) {
            return new TodoCommand(command);
        } else if (command.length() >= 8 && command.substring(0, 8).equals("deadline")) {
            return new DeadlineCommand(command);
        } else if (command.length() >= 5 && command.substring(0, 5).equals("event")) {
            return new EventCommand(command);
        } else if (command.length() >= 6 && command.substring(0, 6).equals("delete")) {
            return new DeleteCommand(command);
        } else {
            throw new InvalidCommandDukeException();
        }
    }
}