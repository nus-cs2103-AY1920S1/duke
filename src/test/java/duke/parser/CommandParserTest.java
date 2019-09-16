package duke.parser;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidDateTimeException;
import duke.exception.InvalidParameterException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class CommandParserTest {
    @Test
    public void parse_success() {
        try {
            assertEquals(CommandParser.parse("todo eat food").getClass(),
                    new AddTodoCommand("eat food").getClass());
            assertEquals(CommandParser.parse("list").getClass(),
                    new ListCommand().getClass());
            assertEquals(CommandParser.parse("done 2").getClass(),
                    new DoneCommand("2").getClass());
            assertEquals(CommandParser.parse("deadline hw /by 31/12/2000 2359").getClass(),
                    new AddDeadlineCommand("hw /by 31/12/2000 2359").getClass());
            assertEquals(CommandParser.parse("event bbq /at 25/12/1965 1734").getClass(),
                    new AddEventCommand("event bbq /at 25/12/1965 1734").getClass());
            assertEquals(CommandParser.parse("delete 5").getClass(),
                    new DeleteCommand("5").getClass());
            assertEquals(CommandParser.parse("bye").getClass(), new ExitCommand().getClass());
        } catch(InvalidCommandException ice) {
            fail();
        } catch(InvalidParameterException ipe) {
            fail();
        }
    }

    @Test
    public void parse_failed_with_ExceptionThrown() {
        Assertions.assertThrows(InvalidCommandException.class, () ->
                CommandParser.parse("hello"));
        Assertions.assertThrows(InvalidParameterException.class, () ->
                CommandParser.parse("todo "));
        Assertions.assertThrows(InvalidParameterException.class, () ->
                CommandParser.parse("deadline sdfdsf"));
        Assertions.assertThrows(InvalidParameterException.class, () ->
                CommandParser.parse("deadline meeting /by "));
        Assertions.assertThrows(InvalidParameterException.class, () ->
                CommandParser.parse("deadline meeting /by 123456789"));
        Assertions.assertThrows(InvalidParameterException.class,
            () -> CommandParser.parse("event sdfdsf"));
        Assertions.assertThrows(InvalidParameterException.class,
            () -> CommandParser.parse("event meeting /at "));
        Assertions.assertThrows(InvalidParameterException.class,
            () -> CommandParser.parse("event meeting /at 123456789"));
        Assertions.assertThrows(InvalidParameterException.class,
            () -> CommandParser.parse("delete task"));
        Assertions.assertThrows(InvalidParameterException.class,
            () -> CommandParser.parse("done task"));
        Assertions.assertThrows(InvalidCommandException.class,
            () -> CommandParser.parse("helloWorld"));
        Assertions.assertThrows(InvalidCommandException.class,
            () -> CommandParser.parse(""));
    }

}
