import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToDoTest {

    @Test
    void toTextFile() {
        ToDo newTodo = new ToDo("I need to test the code");
        assertEquals("T | 0 | I need to test the code", newTodo.toTextFile());
    }

    @Test
   void testToString() {
        ToDo newTodo = new ToDo("I need to test the code");
        assertEquals("[T]" + "[\u2718]" + " " + "I need to test the code", newTodo.toString());
   }

   @Test
    void getStatusIcon() {
        ToDo newToDo = new ToDo("I need to test the code");
        assertEquals("[\u2718]", newToDo.getStatusIcon());
   }

   @Test
    void markAsDone() {
       ToDo newToDo = new ToDo("I need to test the code");
       newToDo.markAsDone();
       assertEquals("[\u2713]", newToDo.getStatusIcon());
   }


}