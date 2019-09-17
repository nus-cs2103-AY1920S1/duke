import duke.command.AddCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class AddCommandTest {

    @Test
    void commandTest() {
        AddCommand cd = new AddCommand("event task /at 12345".split(" ", 2));
        assertFalse(AddCommand.isValidTime("12345"));
    }
}
