package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testStringConversion() {
        assertEquals("[D][✘] individual project (by: 28/08/2019 2359)",
                new Deadline("individual project", "28/08/2019 2359").toString());
    }

    @Test
    public void testStringFormatter() {
        assertEquals("D | ✘ | individual project | 28/08/2019 2359",
                new Deadline("individual project", "28/08/2019 2359").format());
    }

    @Test
    public void testStatusIconGetter() {
        assertEquals("✘",
                new Deadline("individual project", "28/08/2019 2359").getStatusIcon());
    }
}