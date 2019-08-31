import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTaskTest {
    @Test
    public void toString_completedTask_tickText() {
        assertEquals("[T][✗] watch webcast",
                new ToDoTask("watch webcast").toString());
    }

}
