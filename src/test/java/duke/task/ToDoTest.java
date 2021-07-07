package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ToDoTest {

    @Test
    void getStorageSyntax() {
        ToDo toDo = new ToDo("Test");
        assertEquals("T | 0 | Test | -1", toDo.getStorageSyntax());
    }

}