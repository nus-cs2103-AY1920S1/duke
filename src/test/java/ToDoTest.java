import duke.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void testStringRepresentation() {
        assertEquals(new Todo("return book to library").toString(),"[T][✘] return book to library");
        assertEquals(new Todo("testing").toString(),"[T][✘] testing");
    }
}
