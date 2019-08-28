import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testStringOutput(){
        String description = "exampleDescription";

        assertEquals("[T][" + "\u2718" + "] exampleDescription",
                new Todo(description).toString());
    }
}