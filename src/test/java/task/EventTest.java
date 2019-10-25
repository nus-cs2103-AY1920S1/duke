package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventTest {

    @org.junit.jupiter.api.Test
    public void toFile() {
        assertEquals("E-0-name-11/11/1991 1700", new Event("name", "11/11/1991 1700").toFile());
    }

    @org.junit.jupiter.api.Test
    public void toString1() {
        assertEquals("[E][✗] name (at: 11 Nov 1991, 0500PM)", new Event("name","11/11/1991 1700").toString());
    }
}