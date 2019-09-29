import kappa.command.AddDeadlineCommand;
import kappa.command.AddEventCommand;
import kappa.command.AddToDoCommand;
import kappa.command.Command;

import kappa.elements.Tags;
import kappa.elements.Parser;

import kappa.exception.KappaException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.Arrays;

/**
 * Test class for Commands.
 */
class CommandTest {

    /**
     * Tests AddToDoCommand.
     *
     * @throws KappaException Throws if there is an error with the parsing.
     */
    @Test
    void testAddToDoCommand() throws KappaException {
        Command command1 = Parser.parse("todo Test1");
        Command command2 = Parser.parse("todo Test2 /t #Tag1 #Tag2");
        assertEquals("Test1", ((AddToDoCommand) command1).getTask());
        assertEquals("Test2", ((AddToDoCommand) command2).getTask());
        assertEquals(new Tags(),((AddToDoCommand) command1).getTags());
        assertEquals(new Tags(Arrays.asList("Tag1", "Tag2")),((AddToDoCommand) command2).getTags());
    }

    /**
     * Tests AddEventCommand.
     *
     * @throws KappaException Throws if there is an error with the parsing.
     */
    @Test
    void testAddEventCommand() throws KappaException {
        Command command1 = Parser.parse("event Test1 /at 12/06/2020 1500");
        Command command2 = Parser.parse("event Test2 /at Friday /t #Tag1 #Tag2");
        assertEquals("Test1", ((AddEventCommand) command1).getTask());
        assertEquals("Test2", ((AddEventCommand) command2).getTask());
        assertEquals(new Tags(),((AddEventCommand) command1).getTags());
        assertEquals(new Tags(Arrays.asList("Tag1", "Tag2")),((AddEventCommand) command2).getTags());
        assertEquals("12th of June 2020, 3.00pm", ((AddEventCommand) command1).getDate());
        assertEquals("Friday", ((AddEventCommand) command2).getDate());
    }

    /**
     * Tests AddDeadlineCommand.
     *
     * @throws KappaException Throws if there is an error with the parsing.
     */
    @Test
    void testAddDeadlineCommand() throws KappaException {
        Command command1 = Parser.parse("deadline Test1 /by 12/06/2020 1500");
        Command command2 = Parser.parse("deadline Test2 /by Friday /t #Tag1 #Tag2");
        assertEquals("Test1", ((AddDeadlineCommand) command1).getTask());
        assertEquals("Test2", ((AddDeadlineCommand) command2).getTask());
        assertEquals(new Tags(),((AddDeadlineCommand) command1).getTags());
        assertEquals(new Tags(Arrays.asList("Tag1", "Tag2")),((AddDeadlineCommand) command2).getTags());
        assertEquals("12th of June 2020, 3.00pm", ((AddDeadlineCommand) command1).getDate());
        assertEquals("Friday", ((AddDeadlineCommand) command2).getDate());
    }

}
