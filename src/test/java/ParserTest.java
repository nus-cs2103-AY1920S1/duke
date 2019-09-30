import duke.command.ExitCommand;
import duke.command.DoneCommand;
import duke.command.DeleteCommand;
import duke.command.AddTodoCommand;
import duke.command.AddDeadlineCommand;
import duke.command.InvalidCommand;
import duke.command.AddEventCommand;
import duke.command.ListCommand;
import duke.command.FindCommand;
import duke.parser.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ParserTest {

    String exit = "bye";
    String list = "list";
    String done = "done 2";
    String todo = "todo read book";
    String deadline = "deadline project /by 5/9/2019 5:00pm";
    String event = "event meeting /at 12/9/2019 16:00";
    String delete = "delete 2";
    String find = "find book";
    String invalid = "kjhsfhdf  ";

    private String[] split(String input) {
        return input.trim().split(" ", 2);
    }

    @Test
    public void parseTest() {
        assertEquals(new ExitCommand(split(exit)).toString(),
                Parser.parse(exit).toString());
        assertEquals(new ListCommand(split(list)).toString(),
                Parser.parse(list).toString());
        assertEquals(new DoneCommand(split(done)).toString(),
                Parser.parse(done).toString());
        assertEquals(new AddTodoCommand(split(todo)).toString(),
                Parser.parse(todo).toString());
        assertEquals(new AddDeadlineCommand(split(deadline)).toString(),
                Parser.parse(deadline).toString());
        assertEquals(new AddEventCommand(split(event)).toString(),
                Parser.parse(event).toString());
        assertEquals(new DeleteCommand(split(delete)).toString(),
                Parser.parse(delete).toString());
        assertEquals(new FindCommand(split(find)).toString(),
                Parser.parse(find).toString());
        assertEquals(new InvalidCommand().toString(),
                Parser.parse(invalid).toString());
    }
}
