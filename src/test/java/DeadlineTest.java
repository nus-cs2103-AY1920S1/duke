import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void dummyTest() {
        Deadline test = null;
        try {
            test = new Deadline("Return book", "12/12/2019 1200");
        } catch (WrongDateFormatDukeException e) {
            e.printStackTrace();
        }
        assertEquals("[D][" + "0" + "] Return book (by: 12/12/2019 1200)", test.toString());
        assertEquals("D | " + "0" + " | Return book | 12/12/2019 1200", test.stringForAppend());
    }
}