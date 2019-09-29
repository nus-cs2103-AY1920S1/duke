import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class DeleteCommandTest {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    @BeforeEach
    void init() {
        storage = new Storage("");
        ui = new Ui();
        taskList = new TaskList(ui);
        taskList.addTask(new Task("test task"));
    }

    @Test
    void testExecute() {
        String input = "1";
        InputStream in = new ByteArrayInputStream((input.getBytes()));
        System.setIn(in);
        try {
            DeleteCommand dc = new DeleteCommand();
            dc.execute(storage, taskList, ui, input);
            assertTrue(taskList.getTaskList().size() == 0);
        } catch (DukeException e) {
            fail("failed to delete item in list");
        }
    }
}
