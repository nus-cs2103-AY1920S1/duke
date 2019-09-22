package duke.task.tasks.entities;

import org.junit.jupiter.api.Test;
import util.DateTime;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TimeFrameTest {

    @Test
    void hasDescription() {
        TimeFrame mockTimeFrameA = new TimeFrame(null, null);
        assertFalse(mockTimeFrameA.hasDescription());

        TimeFrame mockTimeFrameB = new TimeFrame(LocalDateTime.now(), null);
        assertTrue(mockTimeFrameB.hasDescription());

        TimeFrame mockTimeFrameC = new TimeFrame(null, LocalDateTime.now());
        assertTrue(mockTimeFrameC.hasDescription());

        TimeFrame mockTimeFrameD = new TimeFrame(LocalDateTime.now(), LocalDateTime.now());
        assertTrue(mockTimeFrameD.hasDescription());
    }

    @Test
    void getDescription() {
        LocalDateTime timeA = LocalDateTime.now();
        LocalDateTime timeB = LocalDateTime.now().plusMinutes(5);

        TimeFrame mockTimeFrameA = new TimeFrame(null, null);
        assertNull(mockTimeFrameA.getDescription());

        TimeFrame mockTimeFrameB = new TimeFrame(timeA, null);
        assertEquals(mockTimeFrameB.getDescription(), "after: " + DateTime.getString(timeA));

        TimeFrame mockTimeFrameC = new TimeFrame(null, timeA);
        assertEquals(mockTimeFrameC.getDescription(), "by: " + DateTime.getString(timeA));

        TimeFrame mockTimeFrameD = new TimeFrame(timeA, timeA);
        assertEquals(mockTimeFrameD.getDescription(), "at: " + DateTime.getString(timeA));

        TimeFrame mockTimeFrameE = new TimeFrame(timeA, timeB);
        assertEquals(mockTimeFrameE.getDescription(), "from: " + DateTime.getString(timeA) +
                " to: " + DateTime.getString(timeB));
    }

    @Test
    void getDateTimes() {
        LocalDateTime timeA = LocalDateTime.now();
        LocalDateTime timeB = LocalDateTime.now().plusMinutes(5);

        TimeFrame mockTimeFrameA = new TimeFrame(null, null);
        assertEquals(0, mockTimeFrameA.getDateTimes().size());

        TimeFrame mockTimeFrameB = new TimeFrame(timeA, null);
        assertEquals(1, mockTimeFrameB.getDateTimes().size());
        assertEquals(timeA, mockTimeFrameB.getDateTimes().get(0));

        TimeFrame mockTimeFrameC = new TimeFrame(null, timeA);
        assertEquals(1, mockTimeFrameC.getDateTimes().size());
        assertEquals(timeA, mockTimeFrameC.getDateTimes().get(0));

        TimeFrame mockTimeFrameD = new TimeFrame(timeA, timeB);
        assertEquals(2, mockTimeFrameD.getDateTimes().size());
        assertEquals(timeA, mockTimeFrameD.getDateTimes().get(0));
        assertEquals(timeB, mockTimeFrameD.getDateTimes().get(1));
    }

    @Test
    void compareTo() {
        LocalDateTime timeA = LocalDateTime.now();
        LocalDateTime timeB = LocalDateTime.now().plusMinutes(5);

        TimeFrame mockTimeFrameA = new TimeFrame(null, null);
        TimeFrame mockTimeFrameB = new TimeFrame(timeA, null);
        TimeFrame mockTimeFrameC = new TimeFrame(null, timeA);
        TimeFrame mockTimeFrameD = new TimeFrame(timeA, timeA);
        TimeFrame mockTimeFrameE = new TimeFrame(timeA, timeB);

        assertEquals(mockTimeFrameA.compareTo(mockTimeFrameA), 0);
        assertEquals(mockTimeFrameA.compareTo(mockTimeFrameB), -1);
        assertEquals(mockTimeFrameA.compareTo(mockTimeFrameC), -1);
        assertEquals(mockTimeFrameA.compareTo(mockTimeFrameD), -1);
        assertEquals(mockTimeFrameA.compareTo(mockTimeFrameE), -1);

        assertEquals(mockTimeFrameB.compareTo(mockTimeFrameC), -1);
        assertEquals(mockTimeFrameC.compareTo(mockTimeFrameB), 1);
        assertEquals(mockTimeFrameC.compareTo(mockTimeFrameD), 0);
        assertEquals(mockTimeFrameC.compareTo(mockTimeFrameE), -1);
        assertEquals(mockTimeFrameC.compareTo(mockTimeFrameE), -1);
    }

    @Test
    void testEquals() {
        LocalDateTime timeA = LocalDateTime.now();
        LocalDateTime timeB = LocalDateTime.now().plusMinutes(5);

        TimeFrame mockTimeFrameA = new TimeFrame(timeA, timeB);
        TimeFrame mockTimeFrameB = new TimeFrame(timeA, timeB);
        TimeFrame mockTimeFrameC = new TimeFrame(timeA, null);

        assertEquals(mockTimeFrameA, mockTimeFrameB);
        assertNotEquals(mockTimeFrameA, mockTimeFrameC);
    }
}