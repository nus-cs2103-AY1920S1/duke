package parser;

import exception.DukeException;
import org.junit.jupiter.api.Test;
import task.Deadline;
import task.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StorageParserTest {

    @Test
    public void parseLine_exampleDeadline_success() {
        try {
            StorageParser sp = new StorageParser();
            assertTrue(sp.parseLine("D//CS2103T HW//true//27/8/2019 2359") instanceof Deadline);
        } catch(DukeException ex) {
            System.out.println("Unexpected exception caught in parseLine_exampleDeadline_success:" + ex.getMessage());
            fail();
        }
    }
    @Test
    public void parseLine_exampleInvalid_exceptionThrown() {
        try {
            StorageParser sp = new StorageParser();
            assertTrue(sp.parseLine("X//CS2103T HW//true//27/8/2019 2359") instanceof Task);
            fail();
        } catch(DukeException ex) {
            assertEquals("Exception while reading contents of state file!", ex.getMessage());
        }
    }
}