package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoTest {

    @Test
    void toString1() {
        Todo todo = new Todo("clean house", false);
        assertEquals("[T][X] clean house", todo.toString());
    }
}