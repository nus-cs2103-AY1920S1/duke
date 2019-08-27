import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

public class StorageTest {
    private final Ui uiManager = new Ui(); 
    private final TaskList taskList = new TaskList();

    @Test
    public void storeTest() {
        Storage storage = new Storage("StoreTest.sav");
        try {
            storage.store(taskList, uiManager);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void retreiveTest() {
        Storage storage = new Storage("RetreiveTest.sav");
        try {
            storage.retrieve(uiManager);
        } catch (DukeException e) {
            fail();
        } catch (IOException e) {
            fail();
        } catch (ClassNotFoundException e) {
            fail();
        }
    }


}