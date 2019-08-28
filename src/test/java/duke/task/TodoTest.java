package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void todo_undoneToString_correctOutput() {
        Todo todo = new Todo("lunch");
        assertEquals("[T][\u2718] lunch", todo.toString());

    }
}
