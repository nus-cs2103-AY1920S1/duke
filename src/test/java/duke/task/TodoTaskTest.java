import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import duke.task.Task;
import duke.task.TaskTodo;

class TodoTaskTest {
    @Test
    void testStringForm_aBunchOfStrings_shouldMatch() {
        Task t = TaskTodo.of("asd");
        assertEquals("T | 0 | asd", t.toFileFormattedString());
    }
}