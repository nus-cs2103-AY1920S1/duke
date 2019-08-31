package tasks;

import duke.tasks.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    ToDo todo;

    ToDoTest(){
        this.todo = new ToDo("description");
    }

    @Test
    void save_taskObject_formattedTaskString(){
        assertEquals("T|0|description", todo.save());
    }

    @Test
    void toString_taskObject_formattedTaskString() {
        assertEquals("[T][✘] description", todo.toString());
    }
}
