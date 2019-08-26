import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventsTest {

    @Test
    public void testDateConversion() throws DukeException {
        assertEquals("Mon Dec 02 20:00:00 SGT 2019", new Events("return books", "02/12/2019 1900-2000").getDate().toString());
    }

    @Test
    public void testStringConversion() throws DukeException {
        assertEquals("[E][✗] return books (at: Mon Dec 02 20:00:00 SGT 2019)", new Events("return books", "02/12/2019 1900-2000").toString());
    }

    @Test
    public void testFileConversion() throws DukeException {
        assertEquals("E | [✗] | return books | 02/12/2019 1900-2000" + "\n", new Events("return books", "02/12/2019 1900-2000").toFileFormat());
    }
}