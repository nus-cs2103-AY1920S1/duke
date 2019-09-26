package utils;

import commands.AddCommand;
import commands.DeleteCommand;
import commands.ListCommand;
import commands.ExitCommand;
import commands.Command;
import exceptions.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;


class ParserTest {


    @Test
    void parse_todoCommand_AddCommand() {
        String testPass = "todo Description";
        String testFail = "todo";
        try {
            Command c = Parser.parse(testPass);
            assertNotNull(c);
            assertTrue(c instanceof AddCommand);
        } catch (Exception e) {
            fail("I want an addCommand object!");
        }
        assertThrows(DukeException.class, () -> Parser.parse(testFail));

    }

    @Test
    void parse_deadlineCommand_AddCommand() {
        String testPass = "deadline Description /by 18-10-2019 18:00";
        try {
            Command c = Parser.parse(testPass);
            assertNotNull(c);
            assertTrue(c instanceof AddCommand);
        } catch (Exception e) {
            fail("I want an addCommand object!");
        }
        String testFailOne = "deadline Description";

        assertThrows(DukeException.class, () -> Parser.parse(testFailOne));

        String testFailTwo = "deadline Description 18-10-2019 18:00";

        assertThrows(DukeException.class, () -> Parser.parse(testFailTwo));

        String testFailThree = "deadline Description /by";

        assertThrows(DukeException.class, () -> Parser.parse(testFailThree));

    }

    @Test
    void parse_eventCommand_AddCommand() {
        String testPass = "event Description /at 18-10-2019 18:00";
        try {
            Command c = Parser.parse(testPass);
            assertNotNull(c);
            assertTrue(c instanceof AddCommand);
        } catch (Exception e) {
            fail("I want an addCommand object!");
        }
        String testFailOne = "event Description";

        assertThrows(DukeException.class, () -> Parser.parse(testFailOne));

        String testFailTwo = "event Description 18-10-2019 18:00";

        assertThrows(DukeException.class, () -> Parser.parse(testFailTwo));

        String testFailThree = "event Description /at";

        assertThrows(DukeException.class, () -> Parser.parse(testFailThree));
    }


    @Test
    void parse_deleteCommand_DeleteCommand() {
        String testPass = "delete 1";
        String testFail = "delete";
        try {
            Command c = Parser.parse(testPass);
            assertNotNull(c);
            assertTrue(c instanceof DeleteCommand);
        } catch (Exception e) {
            fail("I want a DeleteCommand object!");
        }
        assertThrows(DukeException.class, () -> Parser.parse(testFail));

    }

    @Test
    void parse_listCommand_ListCommand() {
        String testPass = "list";
        try {
            Command c = Parser.parse(testPass);
            assertNotNull(c);
            assertTrue(c instanceof ListCommand);
        } catch (Exception e) {
            fail("I want a ListCommand object!");
        }
    }

    @Test
    void parse_byeCommand_ExitCommand() {
        String testPass = "bye";
        try {
            Command c = Parser.parse(testPass);
            assertNotNull(c);
            assertTrue(c instanceof ExitCommand);
        } catch (Exception e) {
            fail("I want a ExitCommand object!");
        }
    }

}
