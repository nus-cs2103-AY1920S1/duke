import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

public class StorageTest {
    Storage storage = new Storage(
            "D:/Marcus Folder/SCHOOL STUFF/YEAR 2/CS2103T/duke/data/duke.txt");

    /**
     * The Duke save file has to be cleared before starting any test,
     * to ensure that the assertions are accurate.
     */
    @BeforeEach
    public void clearDukeSaveFileBeforeTest() {
        try {
            storage.updateSaveFile(new ArrayList<Task>());
        } catch (IceBearException e) {
            fail(e.toString());
        }
    }

    /**
     * The duke save file has to be cleared after all the tests to clean up the save file.
     */
    @AfterAll
    public static void clearDukeSaveFileAfterTest() {
        try {
            Storage storage = new Storage(
                    "D:/Marcus Folder/SCHOOL STUFF/YEAR 2/CS2103T/duke/data/duke.txt");
            storage.updateSaveFile(new ArrayList<Task>());
        } catch (IceBearException e) {
            fail(e.toString());
        }
    }

    @Test
    public void updateSaveFile() {
        try {
            Ui ui = new Ui();
            ArrayList<Task> tasks = new ArrayList<>();

            tasks.add(Task.create("todo abc"));
            storage.updateSaveFile(tasks);
            assertEquals(tasks.get(0).toString(), storage.load().get(0).toString());

            tasks.add(Task.create("event abc /at 1/1/1997 2335"));
            storage.updateSaveFile(tasks);
            assertEquals(tasks.get(0).toString(), storage.load().get(0).toString());

            tasks.get(0).setDone();
            storage.updateSaveFile(tasks);
            assertEquals(tasks.get(0).toString(), storage.load().get(0).toString());

            tasks.remove(0);
            storage.updateSaveFile(tasks);
            assertEquals(tasks.get(0).toString(), storage.load().get(0).toString());

        } catch (IceBearException e) {
            fail(e.toString());
        }
    }

    @Test
    public void addToSaveFile() {
        try {
            Task t = Task.create("event abc /at 1/1/1997 2335");
            storage.addNew(t.toFileString());
            assertEquals(t.toString(), storage.load().get(0).toString());

            Task t2 = Task.create("event abcd /at 1/2/1997 2335");
            storage.addNew(t2.toFileString());
            assertEquals(t2.toString(), storage.load().get(1).toString());
        } catch (IceBearException e) {
            fail(e.toString());
        }
    }

}
