import org.junit.jupiter.api.Assertions;

import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

class DukeTest {
    public static void main(String[] args) {
        TaskList test = new TaskList("data/TaskListTest1");
        test.addToTodo("Test1 Description", "event", "at 2/12/2019 1800" );
        Assertions.assertEquals("[E][✗] Test1 Description(at 02/12/19 0600)", test.lst.get(0).toString());
        System.out.println("Tested");
        return;
    }

}