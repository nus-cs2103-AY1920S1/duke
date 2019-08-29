import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.io.File;

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
    public void retreiveTest() throws IOException {
        File file = new File("RetreiveTest.sav");
        if(!file.exists()) {
            file.createNewFile();
        }
        Storage storage = new Storage("RetreiveTest.sav");
        try {
            storage.retrieve();
        } catch (DukeException e) {
            fail();
        } catch (IOException e) {
            fail("IOException occured");
        } catch (ClassNotFoundException e) {
            fail("ClassNotFoundException occured");
        }
    }


}