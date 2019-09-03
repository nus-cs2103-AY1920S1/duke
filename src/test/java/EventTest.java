import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testGetDateTime() {
        String description = "exampleDescription";
        LocalDateTime localDateTime = LocalDateTime.of(2019, 8, 12, 12, 50);

        assertEquals(2, new Event(description, localDateTime).getAt().split(" ").length);
    }
}