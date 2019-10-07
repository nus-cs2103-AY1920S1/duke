package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testStringConversion() {
        assertEquals("[D][X] individual project (by: Wed, 28 Aug 2019, 11:59PM)",
                new Deadline("individual project", "Wed, 28 Aug 2019, 11:59PM").toString());
    }

    @Test
    public void testStringFormatter() {
        assertEquals("D | X | individual project | Wed, 28 Aug 2019, 11:59PM",
                new Deadline("individual project", "Wed, 28 Aug 2019, 11:59PM").format());
    }

    @Test
    public void testStatusIconGetter() {
        assertEquals("X",
                new Deadline("individual project", "Wed, 28 Aug 2019, 11:59PM").getStatusIcon());
    }
}