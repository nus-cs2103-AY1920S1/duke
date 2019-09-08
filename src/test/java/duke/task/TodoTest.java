package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoTest {

    @Test
    void todoToStringManualInputCorrectFormat() {
        TodoStub todo = new TodoStub("Test", false);
        assertEquals("[T][X] Test", todo.toString());
    }
}