import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void toTaskTest() {
        LocalDateTime t1 = LocalDateTime.of(1990,12,12,5,10);
        Event event1 = new Event("event", t1, t1, true);
        assertEquals(event1.toString(), Task.toTask(event1.toString()).toString());
    }
}
