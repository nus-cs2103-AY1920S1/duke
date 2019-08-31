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
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void retreiveTest() throws DukeException {
        File file = new File("RetreiveTest.sav");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new DukeException(e.getMessage());
            }
        }
        Storage storage = new Storage("RetreiveTest.sav");
        try {
            storage.retrieve();
        } catch (DukeException e) {
            fail();
        }
    }


}