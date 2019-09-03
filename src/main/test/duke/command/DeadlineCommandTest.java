package duke.command;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import static org.junit.jupiter.api.Assertions.*;

class DeadlineCommandTest {

    @Test
    void testMakeDate() {
        DeadlineCommand deadlineCommand = new DeadlineCommand();
        try {
            assertEquals("18th of December 2019, 6:00pm", deadlineCommand.makeDate("18/12/19"));
        } catch(ParseException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void test_makeDate_wrongDate_ParseException() {
        DeadlineCommand deadlineCommand = new DeadlineCommand();
        try {
            deadlineCommand.makeDate("Sunday");
        } catch(ParseException e) {
            assertEquals("Unparseable date: 'Sunday'", e.getMessage());
        }
    }
}