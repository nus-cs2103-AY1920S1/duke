package duke;

import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class TaskTest {

    @Test
    public void test_constructor(){
        assertEquals("[T][" + "\u2718" + "] do tutorials", (new Todo("do tutorials")).toString());
    }

    @Test
    public void test_markAsDone() {
        Todo todo = new Todo("do work");
        todo.markAsDone();
        assertEquals(true, todo.getIsDone());
    }

    @Test
    public void test_getDescription() {
        Todo todo = new Todo("do work");
        assertEquals("do work", todo.getDescription());
    }

    @Test
    public void test_getStatusIcon() {
        Todo todo = new Todo("do work");
        assertEquals("\u2718", todo.getStatusIcon());
    }
}
