package duke.command;

import duke.exception.DukeException;
import duke.parser.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddCommandTest {


    // Test to-do Formats
    @Test
    public void parseDescription_normalToDo_parsedCorrectly() {
        try {
            assertEquals("Make the bed",
                    ((AddCommand) Parser.parse("todo Make the bed")).parseDescription());
        } catch (DukeException e) {
            System.err.println(e);
        }
    }

    @Test
    public void parseDescription_emptyToDo_exceptionThrown() {
        try {
            ((AddCommand) Parser.parse("todo")).parseDescription();
        } catch (DukeException e) {
            String expected = "☹ OOPS!!! The description of a todo cannot be empty";
            assertEquals(expected, e.getMessage());
        }
    }


    // Test Deadline Formats
    @Test
    public void parseDescription_emptyDeadline_exceptionThrown() {
        try {
            ((AddCommand) Parser.parse("deadline")).parseDescription("/by");
        } catch (DukeException e) {
            String expected = "☹ OOPS!!! The description of a deadline cannot be empty";
            assertEquals(expected, e.getMessage());
        }
    }

    // Test Event Formats
    @Test
    public void parseDescription_normalEvent_parsedCorrectly() {
        try {
            assertEquals("Make the bed ",
                    ((AddCommand) Parser.parse("event Make the bed /at 2/2/2019")).parseDescription("/at"));
        } catch (DukeException e) {
            System.err.println(e);
        }
    }

    @Test
    public void parseDescription_emptyEvent_exceptionThrown() {
        try {
            ((AddCommand) Parser.parse("event")).parseDescription("/at");
        } catch (DukeException e) {
            String expected = "☹ OOPS!!! The description of a event cannot be empty";
            assertEquals(expected, e.getMessage());
        }
    }

    @Test
    public void parseDescription_incorrectEvent_exceptionThrown() {
        try {
            ((AddCommand) Parser.parse("event Make the bed")).parseDescription("/at");
        } catch (DukeException e) {
            String expected = "☹ OOPS!!! Incorrect format. \nPlease try again with the format below: \n" +
                    "event ($task_name) /at ($date/day)";
            assertEquals(expected, e.getMessage());
        }
    }

    @Test
    public void parseDescription_incompleteEvent_exceptionThrown() {
        try {
            ((AddCommand) Parser.parse("event Make the bed /at")).parseDescription("/at");
        } catch (DukeException e) {
            String expected = "☹ OOPS!!! Incorrect format. \nPlease try again with the format below: \n" +
                    "event ($task_name) /at ($date/day)";
            assertEquals(expected, e.getMessage());
        }
    }

    // Test Parsing of Dates for Deadlines and Events
    @Test
    public void parseDate_AcceptedDateFormat_parsedCorrectly() {
        try {
            assertEquals("Sat, 2 Feb 2019, 08:00 PM",
                    ((AddCommand) Parser.parse("deadline Make the bed /by 2/2/2019 2000")).parseDate());
        } catch (DukeException e) {
            System.err.println(e);
        }
    }

    @Test
    public void parseDate_StringDateFormat_parsedCorrectly() {
        try {
            assertEquals("2/2/2019 8pm",
                    ((AddCommand) Parser.parse("deadline Make the bed /by 2/2/2019 8pm")).parseDate());
        } catch (DukeException e) {
            System.err.println(e);
        }
    }
}
