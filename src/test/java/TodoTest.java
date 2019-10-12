import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void  testTodoGetIsDone() {
        Todo td = new Todo("assignments");
        assertEquals(false, td.getIsDone());
    }
}
