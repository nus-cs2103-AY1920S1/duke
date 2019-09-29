package duke.util;

import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDateTime;

import static duke.util.ObjectsForTest.DEADLINE2;
import static duke.util.ObjectsForTest.EVENT;
import static duke.util.ObjectsForTest.TODO1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

class ParserTest {

    public static final String TODO_COMMAND_STR = "todo Sleep early";
    public static final String DEADLINE_COMMAND_STR = "deadline Sleep /by 31/5/2020 11:00";
    public static final String EVENT_COMMAND_STR = "event Sleep /at 31/5/2020 11:00-23:00";

    @Test
    void parseDateTime() {
        assertEquals(LocalDateTime.parse("2019-05-31T12:22"), Parser.parseDateTime("31/05/2019 12:22"));
        assertEquals(LocalDateTime.parse("2019-05-31T12:22"), Parser.parseDateTime("31/5/2019 12:22"));
    }

    @Test
    void parseCommand_bye_success() {
        try {
            assertEquals(new ExitCommand().getClass(), Parser.parseCommand("bye").getClass());
            assertEquals(new ExitCommand().getClass(), Parser.parseCommand("   bye   ").getClass());
        } catch (DukeException e) {
            fail("bye command is parsed wrongly");
        }
    }

    @Test
    void parseCommand_list_success() {
        try {
            assertEquals(new ListCommand().getClass(), Parser.parseCommand("list").getClass());
            assertEquals(new ListCommand().getClass(), Parser.parseCommand("   list   ").getClass());
        } catch (DukeException e) {
            fail("list command is parsed wrongly");
        }
    }

    @Test
    void parseCommand_find_success() {
        try {
            assertEquals(new FindCommand("early"), Parser.parseCommand("find early"));
        } catch (DukeException e) {
            fail("find command is parsed wrongly");
        }
    }

    @Test
    void parseCommand_done_success() {
        try {
            assertEquals(new DoneCommand(0), Parser.parseCommand("done 1"));
            assertEquals(new DoneCommand(0), Parser.parseCommand("   done   1"));
        } catch (DukeException e) {
            fail("done command is parsed wrongly");
        }
    }

    @Test
    void parseCommand_delete_success() {
        try {
            assertEquals(new DeleteCommand(0), Parser.parseCommand("delete 1"));
            assertEquals(new DeleteCommand(0), Parser.parseCommand("   delete   1"));
        } catch (DukeException e) {
            fail("delete command is parsed wrongly");
        }
    }

    @Test
    void parseCommand_add_success() {
        try {
            assertEquals(new AddCommand(TODO1), Parser.parseCommand(TODO_COMMAND_STR));
            assertEquals(new AddCommand(DEADLINE2), Parser.parseCommand(DEADLINE_COMMAND_STR));
            assertEquals(new AddCommand(EVENT), Parser.parseCommand(EVENT_COMMAND_STR));
        } catch (DukeException e) {
            fail("add related command is parsed wrongly");
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"hehe", "todo  ", "deadline hehe", "deadline /by", "deadline /by 02/21/1999",
            "event /by 02/21/1999", "event /at", "event /at 02/21/1999 18:00", "find  "})
    void parseCommand_invalidCommand_throwDukeException(String invalidCommand) {
        assertThrows(DukeException.class, () -> Parser.parseCommand(invalidCommand));
    }
}