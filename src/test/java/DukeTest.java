import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class DukeTest{
    TaskList taskList = new TaskList();

    @Test
    public void testAddGet(){
        taskList.add(new Todo("eat salad", 0));
        assertEquals(taskList.get(0), new Todo("eat salad", 0));
    }
    @Test
    public void testRemove(){
        Task t = taskList.remove(0);
        assertEquals(t, new Todo("eat salad", 0));
    }
    @Test
    public void testVerify(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        assertThrows(DukeException.class, () -> Verify.validCommand("blah", taskList, formatter));
    }
}