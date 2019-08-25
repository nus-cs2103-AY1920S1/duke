package duck.util;

import duck.command.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static duck.util.ObjectsForTest.*;
import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void parseDateTime_normal() {
        assertEquals(LocalDateTime.parse("2019-05-31T12:22"), Parser.parseDateTime("31/05/2019 12:22"));
        assertEquals(LocalDateTime.parse("2019-05-31T12:22"), Parser.parseDateTime("31/5/2019 12:22"));
    }

    @Test
    void parseCommand_list() {
        try {
            assertEquals(new ListCommand().getClass(), Parser.parseCommand("list").getClass());
            assertEquals(new ListCommand().getClass(), Parser.parseCommand("   list   ").getClass());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    void parseCommand_bye() {
        try {
            assertEquals(new ExitCommand().getClass(), Parser.parseCommand("bye").getClass());
            assertEquals(new ExitCommand().getClass(), Parser.parseCommand("   bye   ").getClass());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    void parseCommand_done() {
        try {
            assertEquals(new DoneCommand(0), Parser.parseCommand("done 1"));
            assertEquals(new DoneCommand(0), Parser.parseCommand("   done   1"));
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    void parseCommand_delete() {
        try {
            assertEquals(new DeleteCommand(0), Parser.parseCommand("delete 1"));
            assertEquals(new DeleteCommand(0), Parser.parseCommand("   delete   1"));
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    void parseCommand_add() {
        try {
            assertEquals(new AddCommand("todo", "Sleep early"), Parser.parseCommand(todoCommand));
            assertEquals(new AddCommand("deadline", "Sleep", Parser.parseDateTime(timeDateStr)),
                    Parser.parseCommand(deadlineCommand));
            assertEquals(new AddCommand("event", "Sleep", Parser.parseDateTime(timeDateStr),
                    LocalTime.parse(timeSTr)), Parser.parseCommand(eventCommand));
        } catch (DukeException e) {
            fail();
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"hehe", "todo  ", "deadline hehe", "deadline /by", "deadline /by 02/21/1999",
            "event /by 02/21/1999", "event /at", "event /at 02/21/1999 18:00"})
    void parseCommand_invalidCommand_throwDuckException(String invalidCommand) {
        assertThrows(DukeException.class, () -> Parser.parseCommand(invalidCommand));
    }
}