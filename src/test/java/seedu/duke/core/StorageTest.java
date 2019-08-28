package seedu.duke.core;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import seedu.duke.exception.DukeException;
import seedu.duke.model.Task;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Tag("basic")
    @Test
    void addTask() throws IOException, DukeException, ParseException{
        Storage storage = new Storage();
        assertEquals("E", new Storage().addTask(new ArrayList<Task>(),
                "event", "project meeting", "28/08/19 12:00").getType());
    }

    @Tag("basic")
    @Test
    void loadTask() throws IOException, ParseException{
        //create a new text file with dummy task values. then load it and then assertEqual with the
        // first element of the list
        List<Task> list = new Storage().loadTask("D:/project/CS2103T/duke/data/duke_test.txt");
        assertEquals(false, list.get(0).getIsDone());
    }
}
