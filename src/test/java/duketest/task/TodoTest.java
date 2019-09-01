package duketest.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import duke.task.Todo;

public class TodoTest {
    @Test
    public void instatiate_instantiateWithDescription_todoObject(){
        Todo todo = new Todo("read book");
        assertTrue(todo instanceof Todo);
    }

    @Test
    public void instatiate_instantiateWithIsDone_todoObject(){
        Todo todo = new Todo("1", "read book");
        assertTrue(todo instanceof Todo);
    }


    @Test
    public void testToString_objectWithDescription_string(){
        assertEquals("T[x] read book\n", new Todo("read book").toString());
    }

    @Test
    public void testToString_objectWithIsDone_string(){
        assertEquals("T[v] read book\n", new Todo("1", "read book").toString());
    }
}