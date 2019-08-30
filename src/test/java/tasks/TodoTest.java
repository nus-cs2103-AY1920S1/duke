package tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TodoTest {

    @Test
    void printForStorage_taskObject_formattedTaskString() {
        assertEquals("T | 0 | description | ", new Todo("description").printForStorage());
    }

    @Test
    void toString_taskObject_formattedTaskString() {
        assertEquals("[T][✗] description", new Todo("description").toString());
    }
}
