import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ParserTest {
    @Test
    public void testProcessTask_success() {
        String[] test1 = new String[]{"todo", "borrow", "book"};
        String[] res1 = new String[]{"todo", "borrow book", ""};
        assertArrayEquals(res1, Parser.processTask(test1));

        String[] test2 = new String[]{"deadline", "return", "book", "/by", "18/8/2019", "2000"};
        String[] res2 = new String[]{"deadline", "return book", "18/8/2019 2000"};
        assertArrayEquals(res2, Parser.processTask(test2));

        String[] test3 = new String[]{"event", "reading", "circle", "/at", "18/8/8/2019", "1700-1900"};
        String[] res3 = new String[]{"event", "reading circle", "18/8/8/2019 1700-1900"};
        assertArrayEquals(res3, Parser.processTask(test3));
    }

    @Test
    public void testProcessTask_fail() {
        String[] test1 = new String[]{"todo"};
        String[] fail1 = new String[]{"The description of a todo cannot be empty.", "", ""};
        assertArrayEquals(fail1, Parser.processTask(test1));

        String[] test2 = new String[]{"deadline", "return book", ""};
        String[] fail2 = new String[]{"The date of a deadline must be in the format \"dd/MM/yyyy HHmm\"", "", ""};
        assertArrayEquals(fail2, Parser.processTask(test2));

        String[] test3 = new String[]{"event", "reading circle", ""};
        String[] fail3 = new String[]{"The date of a event must be in the format \"dd/MM/yyyy HHmm-HHmm\"", "", ""};
        assertArrayEquals(fail3, Parser.processTask(test3));
    }
}
