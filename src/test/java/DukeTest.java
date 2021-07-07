import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

class DukeTest {

    public void taskListGet_success() {
        Task t = new Task("test");
        ArrayList<Task> tasks = new ArrayList<Task>();
        tasks.add(t);
        TaskList tl = new TaskList(tasks);
        try {
            System.out.println(TaskList.get(1).toString());
        } catch (Exception e) {
            fail();
        }
    }

    public void missingFile_exceptionThrown() {
        try {
            Duke d = new Duke("data/notexist/asd.dmp");
            fail();
        } catch (Exception e) {
            assertEquals(
                    "java.io.FileNotFoundException: data/notexist/asd.tmp (No such file or directory)",
                    e.getMessage());
        }
    }
}
