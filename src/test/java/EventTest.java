import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void finishTest(){
        LocalDateTime t1 = LocalDateTime.of(1990,1,1,0,0);
        Event event1 = new Event("event", t1, t1, true);
        Event event2 = new Event("event", t1, t1, false);
        assertEquals(event1.toString(), event2.finish().toString());
    }
}