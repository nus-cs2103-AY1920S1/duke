import command.Command;
import command.DoneCommand;
import command.ListCommand;
import command.ToDoCommand;
import command.DeadlineCommand;
import command.EventCommand;
import command.DeleteCommand;
import command.ByeCommand;
import command.InvalidCommand;

/**
 * Parser to understand user inputs.
 */
public class Parser {

    /**
     * Parser is responsible for understanding the user's input. It will parse the
     * input and react with a suitable command.
     * 
     * @param userInput users's input as String
     * @return a Command
     * @See Command
     */
    public static Command parse(String userInput) {
        String[] input = userInput.split(" ");
        if (input[0].equals("list")) { // list out
            return new ListCommand();
        } else if (input[0].equals("done")) { // done
            return new DoneCommand(input);
        } else if (input[0].equals("todo")) { // todo task
            return new ToDoCommand(input);
        } else if (input[0].equals("deadline")) { // deadline task
            return new DeadlineCommand(input);
        } else if (input[0].equals("event")) { // event task
            return new EventCommand(input);
        } else if (input[0].equals("delete")) { // delete
            return new DeleteCommand(input);
        } else if (input[0].equals("bye")) { // bye
            return new ByeCommand();
        } else if (input[0].equals("find")) { // find
            return new FindCommand(input);
        } else {
            return new InvalidCommand();
        }
    }
}
