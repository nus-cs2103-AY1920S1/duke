package duke.storage;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SerializerTest {

    @Test
    void deserializeTask_invalidFormat_failMessage() {
        try {
            Serializer s = new Serializer();
            assertEquals("Unable to deserialize task from text.", s.deserializeTask("hello"));
            assertEquals("Unable to deserialize task from text.", s.deserializeTask("[T]|0|gg"));
            assertEquals("Unable to deserialize task from text.", s.deserializeTask("[Todo] buy book"));
            assertEquals("Unable to deserialize task from text.", s.deserializeTask(""));
        } catch (DukeException e) {
            assertEquals("Unable to deserialize task from text.", e.getMessage());
        }
    }

}