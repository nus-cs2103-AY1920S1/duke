package org.duke.ui;

import org.duke.cmd.Command;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CommandTest {
    @Test
    public void testBasicCommand() {
        Command c = Command.parse("hello world");
        assertNotNull(c);
        assertEquals(c.type, "hello");
        assertEquals(c.arguments, "world");
        assertTrue(c.namedArguments.isEmpty());
    }

    @Test
    public void testSwitchCommand() {
        Command c = Command.parse("one two three /four /five six");
        assertNotNull(c);
        assertEquals(c.type, "one");
        assertEquals(c.arguments, "two three");
        assertEquals(c.namedArguments.size(), 2);
        assertEquals(c.namedArguments.get("four"), "");
        assertEquals(c.namedArguments.get("five"), "six");
    }
}
