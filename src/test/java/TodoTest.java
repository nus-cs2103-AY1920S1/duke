import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class TodoTest {
    @Test
    public void toStringTest() {
        String todo = "[T][-] test";
        Task testTask = new Todo("test");
        assertEquals(todo, testTask.toString());
    }
}
