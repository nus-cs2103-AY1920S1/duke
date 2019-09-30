package seedu.duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    void testToString() {
        assertEquals("[T][\u2718] running home", new Todo("running home").toString());
    }
}

