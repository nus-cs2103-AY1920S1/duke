package duke.task;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskTest {
    @Test
    void getSaveList_incompleteTask_success() {
        Task task = new Task("desc");
        assertEquals(List.of("0", "desc"), task.getSaveList());
    }

    @Test
    void getSaveList_doneTask_success() {
        Task task = new Task("desc");
        task.markAsDone();
        assertEquals(List.of("1", "desc"), task.getSaveList());
    }

    @Test
    void toString_incompleteTask_success() {
        Task task = new Task("desc");
        String crossSymbol = "\u2718"; // X symbol
        assertEquals("[" + crossSymbol + "] desc", task.toString());
    }

    @Test
    void toString_doneTask_success() {
        Task task = new Task("desc");
        task.markAsDone();
        String tickSymbol = "\u2713"; // tick symbol
        assertEquals("[" + tickSymbol + "] desc", task.toString());
    }
}
