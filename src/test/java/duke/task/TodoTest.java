package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TodoTest {
    @Test
    public void createTodoTest1() {
        Todo t = new Todo("homework");
        assertEquals(t.toString(), "[T][X] homework");
    }

    @Test
    public void createTodoTest2() {
        Todo t = new Todo("homework", true);
        assertEquals(t.toString(), "[T][O] homework");
    }

    @Test
    public void isCompletedTest1() {
        Todo t = new Todo("homework", true);
        assertEquals(t.isCompleted(), true);
    }

    @Test
    public void isCompletedTest2() {
        Todo t = new Todo("homework");
        assertEquals(t.isCompleted(), false);
    }
}