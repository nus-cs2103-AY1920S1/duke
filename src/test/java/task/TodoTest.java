package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TodoTest {

    @org.junit.jupiter.api.Test
    public void toFile() {
        assertEquals("T-0-name", new Todo("name").toFile());
    }

    @org.junit.jupiter.api.Test
    public void toString1() {
        assertEquals("[T][✗] name", new Todo("name").toString());
    }
}