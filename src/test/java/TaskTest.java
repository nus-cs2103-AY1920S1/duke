import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskTest {

    //Create dummy object
    private Task createDummyTask() {
        Task dummyTask = new Task("random activity");
        return dummyTask;
    }

    @Test
    void isDone_default_shouldBeFalse() {
        Task dummyTask = createDummyTask();
        assertEquals(false, dummyTask.isDone());
    }

    @Test
    void testMarkAsDone() {
        Task dummy = createDummyTask();
        assertEquals(false, dummy.isDone());
        dummy.markAsDone();
        assertEquals(true, dummy.isDone());
    }

    @Test
    void testQuietMarkAsDone() {
        Task dummy = createDummyTask();
        assertEquals(false, dummy.isDone());
        dummy.markAsDone();
        assertEquals(true, dummy.isDone());
    }


    @Test
    void getStatusIcon_notDone_negative() {
        Task dummy = createDummyTask();
        assertEquals(" ", dummy.getStatusIcon());
    }

    @Test
    void getStatusIcon_isDone_positive() {
        Task dummy = createDummyTask();
        dummy.markAsDone();
        assertEquals("+", dummy.getStatusIcon());
    }

    @Test
    void testGetDescription() {
        Task dummy = createDummyTask();
        assertEquals("random activity", dummy.getDescription());
    }

    @Test
    void testToString() {
        Task dummy = createDummyTask();
        assertEquals("[ ] random activity", dummy.toString());
    }
}