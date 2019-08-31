import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {

    Task check = new Deadline("return book", "04/06/2019 1030");

    @Test
    void ToString() {
        String expected = "[D][" + "\u2718" + "] return book (by: Tue Jun 04 10:30:00 SGT 2019)";
        assertEquals(expected, check.toString());
    }

    @Test
    void toSave() {
        String expected = "D | 0 | return book | 04/06/2019 1030";
        assertEquals(expected, check.toSave());
    }
}