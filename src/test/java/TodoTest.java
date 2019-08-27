import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void constructTodoTest() {
        Todo test = new Todo("buy bread");
        assertEquals(test.toString(), "[T][" + "\u2718" + "] buy bread");
    }
}
