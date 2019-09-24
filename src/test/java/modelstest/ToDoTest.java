package modelstest;

import models.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ToDoTest {

    @Test
    void testTextFileString() {
        ToDo toDoTest = new ToDo("Testing ToDo class");
        assertEquals("T | 0| Testing ToDo class", toDoTest.toString());
    }

}