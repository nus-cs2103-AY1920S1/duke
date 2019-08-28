package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void testDeadline(){
        assertEquals("[T][\u2713] read books", new Todo("read books"));
    }
}