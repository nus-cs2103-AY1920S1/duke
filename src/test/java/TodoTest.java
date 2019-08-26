import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void testStringConversion() {
        assertEquals("[T][✗] return books", new Todo("return books").toString());
    }

    @Test
    public void testFileConversion() {
        assertEquals("T | [✗] | return books" + "\n", new Todo("return books").toFileFormat());
    }

}
