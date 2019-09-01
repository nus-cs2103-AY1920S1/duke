import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class DoneCommandTest {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    @BeforeEach
    void init() {
        storage = new Storage("");
        taskList = new TaskList();
        taskList.addTask(new Task("test task"));
    }

    @Test
    void testExecute() {
        String input = "1";
        InputStream in = new ByteArrayInputStream((input.getBytes()));
        System.setIn(in);
        ui = new Ui();
        try {
            DoneCommand dc = new DoneCommand();
            dc.execute(storage, taskList, ui);
            assertTrue(taskList.getTask(1).getIsDone());
        } catch (DukeException e) {
            fail("failed to change item in list to done");
        }
    }
}
