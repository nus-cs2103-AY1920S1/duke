import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    Ui ui = new Ui();
    TaskList taskList = new TaskList(ui);
    Storage storage = new Storage(taskList, System.getProperty("user.dir") + "/data/duke.txt");

    @Test
    void storage_LoadFromTextFile() throws DukeException {
        storage.readTask();
        assertEquals(taskList.getSize(), 2);
    }
}
