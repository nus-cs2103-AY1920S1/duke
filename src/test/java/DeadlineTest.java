import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testGetDateTime(){
        String description = "exampleDescription";
        LocalDateTime localDateTime = LocalDateTime.of(2019, 8, 12, 12, 50);

        assertEquals(2, new Deadline(description, localDateTime).getBy().split(" ").length);
    }
}