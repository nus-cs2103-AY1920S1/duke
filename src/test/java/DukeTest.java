
import duck.util.Storage;
import duck.util.TaskList;
import duck.util.Ui;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static duck.util.ObjectsForTest.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {

    private static final String input = "todo Sleep early\ndeadline Sleep /by 31/5/2020 11:00\n"
            + "event Sleep /at 31/5/2020 11:00-23:00\n" + "list\ndone 1\ndelete 2\nlist\nbye\n";

    private static final InputStream in = new ByteArrayInputStream(input.getBytes());

    private static final String filePath = "/Users/xiaoyu/duke/data/duke.txt";


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
        System.setIn(System.in);
        System.setOut(System.out);
    }

    @Test
    public void run_addNormalInput(){
        Duke duke = new Duke(filePath);
        duke.run();
        assertEquals(list, duke.getTaskList());
    }
}
