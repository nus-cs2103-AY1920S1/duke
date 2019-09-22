import duke.Date;
import duke.Todo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DukeTest {

    /**
     * Tests if a Date object returns the correct string format for printing.
     */
    @Test
    void testDate() {
        Date date = new Date(15, 7, 2019);
        assertEquals("15/07/2019", date.toString());
    }


    /**
     * Tests if a ToDo object returns the correct string format for printing.
     */
   @Test
   void testTodoFormat() {
        Todo todo = new Todo(1, "read book", "T");
        assertEquals("[T][NOT DONE] read book", todo.toString());
   }
}
