import duke.task.ToDo;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public static void todoTest() {
        assertEquals("[T][N] what",
                new ToDo("what").toString());
    }
}