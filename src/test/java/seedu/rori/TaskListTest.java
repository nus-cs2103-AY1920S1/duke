import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {

    @Test
    public void addDeleteTest() {
        TaskList taskList = new TaskList();

        try {
            taskList.addTask(new Todo("This is a Todo."));
            taskList.addTask(new Deadline("This is a Deadline.", 
                    LocalDate.parse("22/02/2020", Task.DATE_FORMATTER),
                    LocalTime.parse("22:22", Task.TIME_FORMATTER)));
            
            taskList.addTask(new Event("This is an Event.", 
                    LocalDate.parse("22/02/2020", Task.DATE_FORMATTER),
                    LocalTime.parse("22:22", Task.TIME_FORMATTER)));
        } catch (Exception e) {
            // Fail but for other reasons
            fail(e.getMessage());
        }

        try {
            taskList.deleteTask(2);
            taskList.deleteTask(0);
            taskList.deleteTask(0);
        } catch (Exception e) {
            fail(e.getMessage());
        }

        try {
            taskList.deleteTask(3);
            fail();
        } catch (Exception e) {
            // Pass
        }
    }    
    
}