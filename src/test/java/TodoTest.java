import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest { 
    @Test 
    private void printTodo() { 
        Todo todo = new Todo("learn guitar");  
        assertEquals("[T][✘] learn guitar", todo.toString()); 
    } 
}
