package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadlineTest {

    @org.junit.jupiter.api.Test
    public void toFile() {
        assertEquals("D-0-name-11/11/1991 1700", new Deadline("name", "11/11/1991 1700").toFile());
    }

    @org.junit.jupiter.api.Test
    public void toString1() {
        assertEquals("[D][✗] name (by: 11 Nov 1991, 0500PM)", new Deadline("name","11/11/1991 1700").toString());
    }
}