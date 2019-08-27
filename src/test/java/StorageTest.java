import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    UI ui = new UI();
    TaskList taskList = new TaskList(ui);
    Storage storage = new Storage(taskList, System.getProperty("user.dir") + "/data/duke.txt");

    @Test
    void storage_LoadFromTextFile() throws IODukeException{
        storage.readTask();
        assertEquals(taskList.getSize(), 2);
    }
}
