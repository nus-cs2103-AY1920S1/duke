import org.junit.jupiter.api.Test;

import task.Todo;
import task.TaskList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {

    @Test
    public void toDo_eat_success() {
        Todo todo = new Todo("eat");
        assertEquals("[T][X] eat", todo.toString());
    }

    @Test
    public void taskListSize_oneTask_success() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("eat"));
        assertEquals(1, taskList.getTasks().size());
    }
}