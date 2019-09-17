package duke.parser;

import duke.command.*;
import duke.exception.IllegalCommandTypeException;
import duke.exception.IllegalDateException;
import duke.exception.IllegalDescriptionException;
import duke.help.HelpInfoOfAddCommand;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    private Parser parser = new Parser();
    @Test
    public void testParseExitCommand() {
        try {
            assertTrue(parser.parseCommand("Bye   abc123 ") instanceof ExitCommand);
        } catch (Exception e){
            fail();
        }
    }

    @Test
    public void testParseListCommand() {
        try {
            Command listCommand = parser.parseCommand("List 1 2");
            assertTrue(listCommand instanceof ListCommand);
        } catch (Exception e){
            fail();
        }
    }

    @Test
    public void testParseDoneCommand() {
        try {
            Command doneCommand = parser.parseCommand("Done [2:10]");
            assertTrue(doneCommand instanceof DoneCommand);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testParseDeleteCommand() {
        try {
            Command deleteCommand = parser.parseCommand("delete    /type todo");
            assertTrue(deleteCommand instanceof DeleteCommand);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parseDeleteCommand_illegalType_exceptionThrown() {
        try {
            parser.parseCommand("deLete   /type   evet");
            fail();
        } catch (IllegalDescriptionException e) {
            assertEquals("Please provide a valid task type.", e.getMessage());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parseDeadlineCommand() {
        try {
            Command deadlineCommand = parser.parseCommand("deadline  task  /by 20/9/2020   15:20  abcd");
            assertTrue(deadlineCommand instanceof DeadlineCommand);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parseEventCommand_onlyTimeProvided_DateEqualsToday() {
        try {
            Command eventCommand = parser.parseCommand("event  task  /at   22:00  abcd");
            AddCommand addCommand = (AddCommand) eventCommand;
            assertEquals(LocalDateTime.of(LocalDate.now(), LocalTime.of(22,0)),
                    ((AddCommand) eventCommand).getTask().getDateTime());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parseDeadlineCommand_illegalDate_exceptionThrown() {
        try {
            parser.parseCommand("deadline   task  /by  20/-3/2020    12:00");
            fail();
        } catch (IllegalDateException e) {
            assertEquals("Please provide a valid date.", e.getMessage());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parseDeadlineCommand_wrongFormat_exceptionThrown() throws IllegalCommandTypeException {
        try {
            parser.parseCommand("deadline   task  /at 20/3/2020  12");
            fail();
        } catch (IllegalDescriptionException e) {
            assertEquals(new HelpInfoOfAddCommand(CommandType.SubCommandType.Deadline).getHelpInformation(),
                    e.getMessage());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testParseFindCommand() {
        try {
            Command findCommand = parser.parseCommand("find   event  t");
            assertTrue(findCommand instanceof FindCommand);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testHelpCommand() {
        try {
            Command helpCommand = parser.parseCommand("help event");
            assertTrue(helpCommand instanceof HelpCommand);
        } catch (Exception e) {
            fail();
        }
    }

}
