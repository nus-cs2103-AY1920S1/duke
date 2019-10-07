import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void Test(){
        Todo todotask = new Todo("read book", true);
        assertEquals("T~1~read book", todotask.toFormattedString());
    }
}