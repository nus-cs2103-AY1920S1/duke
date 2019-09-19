package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ToDoTest {

    @Test
    void testToString() {
        assertEquals("[T] | 1 | play",
                new ToDo("play").toDataBase());
    }
}