import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.FileReader;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {
    Storage s = new Storage(
            "D:/Marcus Folder/SCHOOL STUFF/YEAR 2/CS2103T/duke/data/duke.txt");
    @BeforeEach
    public void clearDukeSaveFileBeforeTest() {
        try {
            s.updateSaveFile(new ArrayList<Task>());
        } catch (DukeException e) {
            fail(e.toString());
        }
    }

    @AfterEach
    public void clearDukeSaveFileAfterTest() {
        try {
            s.updateSaveFile(new ArrayList<Task>());
        } catch (DukeException e) {
            fail(e.toString());
        }
    }

    @Test
    public void updateSaveFile() {
        try {
            Ui ui = new Ui();
            ArrayList<Task> tasks = new ArrayList<>();

            tasks.add(Task.create("todo abc"));
            s.updateSaveFile(tasks);
            assertEquals(tasks.get(0).toString(), s.load().get(0).toString());

            tasks.add(Task.create("event abc /at 1/1/1997 2335"));
            s.updateSaveFile(tasks);
            assertEquals(tasks.get(0).toString(), s.load().get(0).toString());

            tasks.get(0).setDone();
            s.updateSaveFile(tasks);
            assertEquals(tasks.get(0).toString(), s.load().get(0).toString());

            tasks.remove(0);
            s.updateSaveFile(tasks);
            assertEquals(tasks.get(0).toString(), s.load().get(0).toString());

        } catch (DukeException e) {
            fail(e.toString());
        }
    }

    @Test
    public void addToSaveFile() {
        try {
            Task t = Task.create("event abc /at 1/1/1997 2335");
            s.addNew(t.toFileString());
            assertEquals(t.toString(), s.load().get(0).toString());

            Task t2 = Task.create("event abcd /at 1/2/1997 2335");
            s.addNew(t2.toFileString());
            assertEquals(t2.toString(), s.load().get(1).toString());
        } catch (DukeException e) {
            fail(e.toString());
        }
    }

}
