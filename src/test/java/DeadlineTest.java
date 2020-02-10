import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testStringConversion() {
        assertEquals("[D][✗] return book (by: 02/02/2020 12:30)",
                (new Deadline("return book", "02/02/2020 12:30")).toString());
    }
}