import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TodoTest {
    @Test
    void constructorTest() {
        String description = "Sample todo";
        Todo notDone = new Todo(description);
        Todo done = new Todo(description, true);

        assertEquals(false, notDone.getIsDone());
        assertEquals(true, done.getIsDone());
    }

    @Test
    void markAsDoneTest() {
        Todo testTodo = new Todo("Sample todo");
        testTodo.markAsDone();

            assertEquals(true, testTodo.getIsDone());
        }
}
