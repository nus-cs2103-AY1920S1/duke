package duke.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CommandTest {
    @Test
    void execute() {
        String[] testCommandArgs = new String[] { "1", "abc", "3"};

        CommandStub commandStub = new CommandStub(testCommandArgs);

        assertFalse(commandStub.didValidate);
        assertFalse(commandStub.didRun);

        assertDoesNotThrow(() -> commandStub.execute(null, null, null));

        assertTrue(commandStub.didValidate);
        assertTrue(commandStub.didRun);

        //commandArgs should be set
        assertEquals(testCommandArgs, commandStub.commandArgs);
    }
}
