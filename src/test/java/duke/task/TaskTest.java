package duke.task;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskTest {
    @Test
    void getSaveList_incompleteTask_success() {
        Task task = new TaskImpl("desc");
        assertEquals(List.of("type", "0", "desc"), task.getSaveList());
    }

    @Test
    void getSaveList_doneTask_success() {
        Task task = new TaskImpl("desc");
        task.markAsDone();
        assertEquals(List.of("type", "1", "desc"), task.getSaveList());
    }

    @Test
    void toString_incompleteTask_success() {
        Task task = new TaskImpl("desc");
        String crossSymbol = "\u2718"; // X symbol
        assertEquals("[type][" + crossSymbol + "] desc", task.toString());
    }

    @Test
    void toString_doneTask_success() {
        Task task = new TaskImpl("desc");
        task.markAsDone();
        String tickSymbol = "\u2713"; // tick symbol
        assertEquals("[type][" + tickSymbol + "] desc", task.toString());
    }
}
