import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * Represents a ParserJUnitTesting which tests Parser.
 * @author Ang Kai Qi
 * @version 0.1.3
 */
public class ParserTest {

    /**
     * Tests Parser.processTask with successful todo, deadline and event inputs.
     */
    @Test
    public void testProcessTask_success() {
        String[] test1 = new String[]{"todo", "borrow", "book"};
        String[] result1 = new String[]{"todo", "borrow book", ""};
        assertArrayEquals(result1, Parser.processTask(test1));

        String[] test2 = new String[]{"deadline", "return", "book", "/by", "18/8/2019", "2000"};
        String[] result2 = new String[]{"deadline", "return book", "18/8/2019 2000"};
        assertArrayEquals(result2, Parser.processTask(test2));

        String[] test3 = new String[]{"event", "reading", "circle", "/at", "18/8/2019", "1700-1900"};
        String[] result3 = new String[]{"event", "reading circle", "18/8/2019 1700-1900"};
        assertArrayEquals(result3, Parser.processTask(test3));
    }

    /**
     * Tests Parser.processTask with todo, deadline and event inputs that are bound to fail.
     */
    @Test
    public void testProcessTask_fail() {
        String[] test1 = new String[]{"todo"};
        String[] fail1 = new String[]{"The description of a todo cannot be empty.\n(Enter \"help\" for commands in "
                + "duke.)", "", ""};
        assertArrayEquals(fail1, Parser.processTask(test1));

        String[] test2 = new String[]{"deadline", "return book", ""};
        String[] fail2 = new String[]{"\u2639 OOPS!!! I'm sorry, but the date of a deadline must be in the format"
                + " \"/by dd/MM/yyyy HHmm\"\n(Enter \"help\" for commands in duke.)", "", ""};
        assertArrayEquals(fail2, Parser.processTask(test2));

        String[] test3 = new String[]{"event", "reading circle", ""};
        String[] fail3 = new String[]{"\u2639 OOPS!!! I'm sorry, but the date of a event must be in the format"
                + " \"/at dd/MM/yyyy HHmm-HHmm\"\n(Enter \"help\" for commands in duke.)", "", ""};
        assertArrayEquals(fail3, Parser.processTask(test3));
    }
}
