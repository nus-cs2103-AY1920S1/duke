import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class DeadlineTest {

    @Test
    public void testDateConversion() throws DukeException {
        assertEquals("Mon Dec 02 19:00:00 SGT 2019", new Deadline("return books", "02/12/2019 1900").getDate().toString());
    }
    @Test
    public void testStringConversion() throws DukeException {
        assertEquals("[D][✗] return books (by: Mon Dec 02 19:00:00 SGT 2019)", new Deadline("return books", "02/12/2019 1900").toString());
    }

    @Test
    public void testFileConversion() throws DukeException {
        assertEquals("D | [✗] | return books | 02/12/2019 1900" + "\n", new Deadline("return books", "02/12/2019 1900").toFileFormat());
    }
}

