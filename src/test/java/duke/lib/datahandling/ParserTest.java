package duke.lib.datahandling;

import duke.lib.ui.UI;
import duke.lib.common.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    @Test
    public void isExitTest() throws DukeException {
        TaskList t = new TaskList();
        DataStorage storage = new DataStorageStub();
        Parser p = new Parser(t, storage);
        p.parse("deadline test /by 20/12/2019 1800");
        assertEquals(false, p.isExit());
        p.parse("todo eat");
        assertEquals(false, p.isExit());
        p.parse("bye");
        assertEquals(true, p.isExit());
    }
}