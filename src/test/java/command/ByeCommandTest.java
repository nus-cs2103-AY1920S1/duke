package command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ByeCommandTest {

    @Test
    void isExit() {
        assertEquals(true, new ByeCommand().isExit());
    }
}