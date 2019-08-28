import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {

    @Test
    void toDataFormatTest() {
        Deadline d = new Deadline("return book","2/12/2019", "1800");
        assertTrue(d.toDataFormat().equals("D | 0 | return book | 2/12/2019, 1800"));
    }

    @Test
    void toStringTest() {
        Deadline d = new Deadline("return book", "2/12/2019", "1800");
        assertTrue(d.toString().equals("[D][✘] return book (by: 2 Dec 2019, 6:00 PM)"));
    }
}