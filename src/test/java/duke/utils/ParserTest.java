package duke.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import duke.exceptions.DukeException;
import duke.commands.Command;
import duke.commands.SaveCommand;

import java.util.ArrayList;

public class ParserTest {
    @Test
    public void testParse_Save_SaveCommand() {
        try {
            Command c = Parser.parse("save");
            assertTrue(c instanceof SaveCommand);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void testParseToDo() {
        try {
            ArrayList<String> commandParams = Parser.parseToDo("     task_with_many_spaces     ");
            assertTrue(commandParams.get(1).equals("task_with_many_spaces"));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void testParseEvent() {
        try {
            String eventString = "    project meeting     /at     26/05/1997 18:00   -    26/05/1997 18:30  ";
            ArrayList<String> commandParams = Parser.parseEvent(eventString);
            boolean boolFirstArg = commandParams.get(1).equals("project meeting");
            boolean boolSecondArg = commandParams.get(2).equals("26/05/1997 18:00");
            boolean boolThirdArg = commandParams.get(3).equals("26/05/1997 18:30");
            assertTrue(boolFirstArg && boolSecondArg && boolThirdArg);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void testParseDeadline() {
        try {
            String deadlineString = "     return library   books     /by     26/05/1997 18:00    ";
            ArrayList<String> commandParams = Parser.parseDeadline(deadlineString);
            boolean boolFirstArg = commandParams.get(1).equals("return library   books");
            boolean boolSecondArg = commandParams.get(2).equals("26/05/1997 18:00");
            assertTrue(boolFirstArg && boolSecondArg);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }
}

