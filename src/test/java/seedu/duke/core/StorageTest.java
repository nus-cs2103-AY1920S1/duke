package seedu.duke.core;

import org.junit.jupiter.api.Test;
import seedu.duke.model.dto.Task;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    /*
    @Test
    void addTask() throws IOException, DukeException, ParseException {
        Storage storage = new Storage();
        assertEquals("E", new Storage().addTask("", new ArrayList<Task>(),
                "event", "project meeting", "28/08/19 12:00"));
    }
    */
    @Test
    void loadTask() throws IOException, ParseException {
        //create a new text file with dummy task values. then load it and then assertEqual with the
        // first element of the list
        List<Task> list = new Storage().loadTask("data/duke_test.txt");
        assertEquals(false, list.get(0).getIsDone());
    }
}
