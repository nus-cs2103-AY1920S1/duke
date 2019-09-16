package tasks;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    private Todo todo; 
    
    public TodoTest() throws ParseException {
        this.todo = new Todo("groceries");
    }
    
    @Test
    void storageStringTest() {
        assertEquals("T | false | groceries", todo.getStorageString());
    }

    @Test
    void listStringTest() {
        assertEquals("[T][✘] groceries", todo.toString());
    }
}