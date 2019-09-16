package modelsTest;

import models.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToDoTest {

    @Test
    void testToString() {
        ToDo toDoTest = new ToDo("Testing ToDo class");
        assertEquals("[T] [\u2718] Testing ToDo class", toDoTest.toString() );
    }

}