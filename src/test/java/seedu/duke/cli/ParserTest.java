package seedu.duke.cli;

import org.junit.jupiter.api.Test;
import seedu.duke.cli.commands.ByeCommand;
import seedu.duke.cli.commands.DeadlineCommand;
import seedu.duke.cli.commands.DeleteCommand;
import seedu.duke.cli.commands.DoneCommand;
import seedu.duke.cli.commands.EventCommand;
import seedu.duke.cli.commands.FindCommand;
import seedu.duke.cli.commands.ListCommand;
import seedu.duke.cli.commands.TodoCommand;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void listTest() throws CommandException {
        assertEquals(Parser.parse("list"), new ListCommand());
    }

    @Test
    public void byeTest() throws CommandException {
        assertEquals(Parser.parse("bye"), new ByeCommand());
    }

    @Test
    public void doneTest() throws CommandException {
        assertEquals(Parser.parse("done 123"), new DoneCommand(123));
    }

    @Test
    public void deleteTest() throws CommandException {
        assertEquals(Parser.parse("delete 123"), new DeleteCommand(123));
    }

    @Test
    public void todoTest() throws CommandException {
        assertEquals(Parser.parse("todo abcdefg"), new TodoCommand("abcdefg"));
    }

    @Test
    public void deadlineNoDateTest() throws CommandException {
        assertEquals(Parser.parse("deadline abcdefg"), new DeadlineCommand("abcdefg", null));
    }

    @Test
    public void deadlineWithDateTest() throws CommandException {
        assertEquals(Parser.parse("deadline abcdefg /by 1/1/2019 00:00"),
                new DeadlineCommand("abcdefg", LocalDateTime.of(2019, 1, 1, 0, 0)));
    }

    @Test
    public void eventNoDateTest() throws CommandException {
        assertEquals(Parser.parse("event abcdefg"), new EventCommand("abcdefg", null));
    }

    @Test
    public void eventWithDateTest() throws CommandException {
        assertEquals(Parser.parse("event abcdefg /at 1/1/2019 00:00"),
                new EventCommand("abcdefg", LocalDateTime.of(2019, 1, 1, 0, 0)));
    }

    @Test
    public void findTest() throws CommandException {
        assertEquals(Parser.parse("find asdasd"), new FindCommand("asdasd"));
    }
}
