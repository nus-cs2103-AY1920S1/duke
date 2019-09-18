 import duke.logic.Parser;

 import duke.command.DeleteCommand;
 import duke.command.PrintListCommand;

 import duke.exception.EmptyTaskDukeException;
 import duke.exception.InvalidInputDukeException;
 import duke.exception.InvalidTaskDukeException;

 import org.junit.jupiter.api.Test;

 import static org.junit.jupiter.api.Assertions.assertEquals;

 public class ParserTest {

    @Test
    public void parseTest() throws EmptyTaskDukeException, InvalidTaskDukeException, InvalidInputDukeException {
        assertEquals(Parser.parse("list"), new PrintListCommand());
        assertEquals(Parser.parse("delete 1"), new DeleteCommand(1));
    }
}
