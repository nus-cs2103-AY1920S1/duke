package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void testDeadline(){
        assertEquals("[T][✘] read books", new Todo("read books").toString());
    }

    @Test
    public void testDone() {
        assertEquals("D", new Deadline("return books", "12/12/2012 1800").toString().substring(1,2));
    }

    @Test
    public void testStatus() {
        boolean test= true;
        Deadline actual = new Deadline("return books", "12/12/2012 1800");
        actual.markAsDone();
        assertEquals(test, actual.isDone);
    }
}