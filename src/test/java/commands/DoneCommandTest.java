package commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.File;
import java.io.IOException;
import core.Storage;
import core.TaskList;
import exceptions.DukeException;
import org.junit.jupiter.api.Test;

class DoneCommandTest {


    @Test
    void doneCommand_nonIntegerIndex_dukeExceptionThrown() throws DukeException, IOException {
        File f = new File("./dukeTasks.txt");
        Storage storage = new Storage(f.getAbsolutePath());
        TaskList taskList = new TaskList(storage.load());

        try {
            new DoneCommand().execute("done a", taskList, storage);
        } catch (DukeException e) {
            assertEquals("OOPS!!! The completed task's index must be a number." , e.getMessage());
        }
    }



}