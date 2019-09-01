import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDosTest {
    @Test
    public void ToDosTestFormatstringOutput() {
        assertEquals("T-0-submit 2103 assignment",
                new ToDos("submit 2103 assignment").formatString());
    }

}
