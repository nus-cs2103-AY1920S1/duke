import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;

public class StorageTest { 
    @Test
    public void storeTest() {
        TaskList taskList = new TaskList();
        Storage storage = new Storage("StoreTest.sav");
        try {
            storage.store(taskList);
        } catch (Exception e) {
            fail();
        }
    }
}