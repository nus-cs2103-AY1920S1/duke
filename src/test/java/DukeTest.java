import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import duke.parser.Parser;
import duke.task.Deadline;

/**
 * Class to test the functionality of Duke.
 */
public class DukeTest {
    /**
     * Tests whether a parser can parse user input of a deadline task.
     */
    @Test
    public void parseDeadlineTest() {
        Parser p = new Parser();
        String input = "Deadline Finish 2103 iP work for this week /by 21/9/2019 1800";
        String expected = "[D][☓] Finish 2103 iP work for this week (by: Sat Sep 21 18:00:00 SGT 2019)";
        Deadline ddl = new Deadline(p.parseDesc(input), p.parseDate(input));
        assertEquals(expected, ddl.toString());
    }
}