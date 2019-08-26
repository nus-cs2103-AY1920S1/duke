import command.Command;
import command.DoneCommand;
import command.ListCommand;
import command.ToDoCommand;
import command.DeadlineCommand;
import command.EventCommand;
import command.DeleteCommand;
import command.ByeCommand;
import command.InvalidCommand;

public class Parser {

    public static Command parse(String userInput) {
        String[] input = userInput.split(" ");
        if (input[0].equals("list")) { //list out
            return new ListCommand();
        } else if (input[0].equals("done")) { //done
            return new DoneCommand(input);
        } else if (input[0].equals("todo")) { //todo task
            return new ToDoCommand(input);
        } else if (input[0].equals("deadline")) { //deadline task
            return new DeadlineCommand(input);
        } else if (input[0].equals("event")) { //event task
            return new EventCommand(input);
        } else if (input[0].equals("delete")) { //delete
            return new DeleteCommand(input);
        } else if (input[0].equals("bye")){ //bye
            return new ByeCommand();
        } else {
            return new InvalidCommand();
        }
    }
}
