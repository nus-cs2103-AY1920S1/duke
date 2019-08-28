import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DukeTest {
    @Test
    void parserTest() {
        assertThrows(DukeException.class, () -> Parser.parse(new String[]{"gibberish"}));
    }

    @Test
    void addTaskTest() {
        TodoList todoList = new TodoList();
        todoList.add(new Todo("desc"));
        assertEquals(1, todoList.length());
        todoList.delete(1);
    }
}
