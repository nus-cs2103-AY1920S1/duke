import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

public class TaskManagerTest {
    @Test
    public void addNewTaskTest(){
        TaskManager testManager = new TaskManager();
        String[] testInput = {"event", "test /at 2/9/2019 2000-2200"};
        try{
            Task expectedTask = new Event("test", "2/9/2019 2000-2200");
            assertEquals(expectedTask.toString(), testManager.addNewTask(testInput).toString());
        }catch (DukeException e){
            fail("DukeException thrown: " + e.getMessage());
        }
    }
}
