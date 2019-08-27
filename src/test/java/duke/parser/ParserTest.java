package duke.parser;

import duke.commands.Command;
import duke.commands.ListCommand;
import duke.tasks.Event;
import duke.tasks.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests Parser class.
 */
public class ParserTest {
    private Parser p;

    @BeforeEach
    public void setUp() {
        p = new Parser();
    }

    /**
     * Parses list command correctly.
     *
     * @throws ParseException
     */
    @Test
    public void parse_listCommand() throws ParseException {
        String input = "list";
        parseAndAssertCommandType(input, ListCommand.class);
    }

    /**
     * Parses task string correctly.
     *
     * @throws ParseException
     */
    @Test
    public void parse_task() throws ParseException {
        String input = "4.[E][\u2718] project meeting (at: Sun, 15 Dec 2019 18:00:00)";
        parseTaskAndAssertTaskType(input, Event.class);
    }

    /**
     * Parses input and asserts the class of the returned command.
     *
     * @param input                string to be parsed
     * @param expectedCommandClass expected class of returned command
     */
    private <T extends Command> void parseAndAssertCommandType(String input, Class<T> expectedCommandClass) throws ParseException {
        Command result = p.parse(input);
        assertTrue(result.getClass().isAssignableFrom(expectedCommandClass));
    }

    /**
     * Parses task and asserts the class of the returned task.
     *
     * @param input
     * @param expectedCommandClass
     * @param <T>
     * @throws ParseException
     */
    private <T extends Task> void parseTaskAndAssertTaskType(String input, Class<T> expectedCommandClass) throws ParseException {
        Task result = p.parseTask(input);
        assertTrue(result.getClass().isAssignableFrom(expectedCommandClass));
    }
}