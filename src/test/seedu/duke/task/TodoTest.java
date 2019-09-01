package seedu.duke.task;

import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    void testToString() {
        assertEquals("[T][\u2718] running home", new Todo("running home").toString());
    }
}

