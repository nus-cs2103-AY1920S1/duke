import duke.util.Storage;
import duke.util.TaskList;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static duke.util.ObjectsForTest.todo;
import static duke.util.ObjectsForTest.deadline;
import static duke.util.ObjectsForTest.event;
import static duke.util.ObjectsForTest.filePath;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class DukeTest {

    private static final String input = "todo Sleep early\ndeadline Sleep /by 31/5/2020 11:00\n"
            + "event Sleep /at 31/5/2020 11:00-23:00\n" + "list\ndone 1\ndelete 2\nlist\nbye\n";

    private static final InputStream in = new ByteArrayInputStream(input.getBytes());
    private static final InputStream originIn = System.in;

    private static TaskList list;

    @BeforeAll
    public static void setUp() throws Exception{
        System.setIn(in);
        list = new TaskList(new Storage(filePath).loadTasks());
        list.add(todo);
        list.add(deadline);
        list.add(event);
        list.getTaskAt(0).markDone();
        list.removeTaskAt(1);
    }

    @AfterAll
    public static void restoreStream() {
        System.setIn(originIn);
    }

    @Test
    public void run_addNormalInput(){
        Duke duke = new Duke(filePath);
        duke.run();
        assertEquals(list, duke.getTaskList());
    }
}
