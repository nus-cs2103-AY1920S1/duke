import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DeadlineTest {

    @Test
    void toDataFormatTest() {
        Deadline d = Deadline.genDeadlineTask("Deadline return book /by 2/12/2019 1800");
        assertTrue(d.toDataFormat().equals("D | 0 | return book | 2/12/2019, 1800"));
    }
    /*
    @Test
    void toStringTest() {
        Deadline d = new Deadline("return book", "2/12/2019", "1800");
        assertTrue(d.toString().equals("[D][\u2718] return book (by: 2 Dec 2019, 6:00 PM)"));
    }
    */
}