package duke.task.tasks.entities;

import duke.task.TimeFrame;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.time.DateTime;

import java.time.LocalDateTime;

class TimeFrameTest {

    @Test
    void hasDescription() {
        TimeFrame mockTimeFrameA = new TimeFrame(null, null);
        Assertions.assertFalse(mockTimeFrameA.hasDescription());

        TimeFrame mockTimeFrameB = new TimeFrame(LocalDateTime.now(), null);
        Assertions.assertTrue(mockTimeFrameB.hasDescription());

        TimeFrame mockTimeFrameC = new TimeFrame(null, LocalDateTime.now());
        Assertions.assertTrue(mockTimeFrameC.hasDescription());

        TimeFrame mockTimeFrameD = new TimeFrame(LocalDateTime.now(), LocalDateTime.now());
        Assertions.assertTrue(mockTimeFrameD.hasDescription());
    }

    @Test
    void getDescription() {
        final LocalDateTime timeA = LocalDateTime.now();
        final LocalDateTime timeB = LocalDateTime.now().plusMinutes(5);

        final TimeFrame mockTimeFrameA = new TimeFrame(null, null);
        Assertions.assertNull(mockTimeFrameA.getDescription());

        final TimeFrame mockTimeFrameB = new TimeFrame(timeA, null);
        Assertions.assertEquals(mockTimeFrameB.getDescription(), "after: " + DateTime.getString(timeA));

        final TimeFrame mockTimeFrameC = new TimeFrame(null, timeA);
        Assertions.assertEquals(mockTimeFrameC.getDescription(), "by: " + DateTime.getString(timeA));

        final TimeFrame mockTimeFrameD = new TimeFrame(timeA, timeA);
        Assertions.assertEquals(mockTimeFrameD.getDescription(), "at: " + DateTime.getString(timeA));

        final TimeFrame mockTimeFrameE = new TimeFrame(timeA, timeB);
        Assertions.assertEquals(mockTimeFrameE.getDescription(), "from: " + DateTime.getString(timeA)
                + " to: " + DateTime.getString(timeB));
    }

    @Test
    void getDateTimes() {
        final LocalDateTime timeA = LocalDateTime.now();
        final LocalDateTime timeB = LocalDateTime.now().plusMinutes(5);

        final TimeFrame mockTimeFrameA = new TimeFrame(null, null);
        Assertions.assertEquals(0, mockTimeFrameA.getDateTimes().size());

        final TimeFrame mockTimeFrameB = new TimeFrame(timeA, null);
        Assertions.assertEquals(1, mockTimeFrameB.getDateTimes().size());
        Assertions.assertEquals(timeA, mockTimeFrameB.getDateTimes().get(0));

        final TimeFrame mockTimeFrameC = new TimeFrame(null, timeA);
        Assertions.assertEquals(1, mockTimeFrameC.getDateTimes().size());
        Assertions.assertEquals(timeA, mockTimeFrameC.getDateTimes().get(0));

        final TimeFrame mockTimeFrameD = new TimeFrame(timeA, timeB);
        Assertions.assertEquals(2, mockTimeFrameD.getDateTimes().size());
        Assertions.assertEquals(timeA, mockTimeFrameD.getDateTimes().get(0));
        Assertions.assertEquals(timeB, mockTimeFrameD.getDateTimes().get(1));
    }

    @Test
    void compareTo() {
        final LocalDateTime timeA = LocalDateTime.now();
        final LocalDateTime timeB = LocalDateTime.now().plusMinutes(5);

        final TimeFrame mockTimeFrameA = new TimeFrame(null, null);
        final TimeFrame mockTimeFrameB = new TimeFrame(timeA, null);
        final TimeFrame mockTimeFrameC = new TimeFrame(null, timeA);
        final TimeFrame mockTimeFrameD = new TimeFrame(timeA, timeA);
        final TimeFrame mockTimeFrameE = new TimeFrame(timeA, timeB);

        Assertions.assertEquals(mockTimeFrameA.compareTo(mockTimeFrameA), 0);
        Assertions.assertEquals(mockTimeFrameA.compareTo(mockTimeFrameB), -1);
        Assertions.assertEquals(mockTimeFrameA.compareTo(mockTimeFrameC), 1);
        Assertions.assertEquals(mockTimeFrameA.compareTo(mockTimeFrameD), 1);
        Assertions.assertEquals(mockTimeFrameA.compareTo(mockTimeFrameE), 1);

        Assertions.assertEquals(mockTimeFrameB.compareTo(mockTimeFrameC), 1);
        Assertions.assertEquals(mockTimeFrameC.compareTo(mockTimeFrameB), -1);
        Assertions.assertEquals(mockTimeFrameC.compareTo(mockTimeFrameD), 0);
        Assertions.assertEquals(mockTimeFrameC.compareTo(mockTimeFrameE), -1);
        Assertions.assertEquals(mockTimeFrameC.compareTo(mockTimeFrameE), -1);
    }

    @Test
    void testEquals() {
        final LocalDateTime timeA = LocalDateTime.now();
        final LocalDateTime timeB = LocalDateTime.now().plusMinutes(5);

        final TimeFrame mockTimeFrameA = new TimeFrame(timeA, timeB);
        final TimeFrame mockTimeFrameB = new TimeFrame(timeA, timeB);
        final TimeFrame mockTimeFrameC = new TimeFrame(timeA, null);

        Assertions.assertEquals(mockTimeFrameA, mockTimeFrameB);
        Assertions.assertNotEquals(mockTimeFrameA, mockTimeFrameC);
    }
}