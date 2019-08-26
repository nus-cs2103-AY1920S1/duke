package task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {

    @Test
    void getBy_sameDateTime_success() {
        Deadline deadline = new Deadline("Deadline", LocalDate.parse("2019-05-10").atStartOfDay());
        assertEquals(deadline.getBy(), LocalDate.parse("2019-05-10").atStartOfDay());
    }

    @Test
    void setBy_sameDateTime_success() {
        Deadline deadline = new Deadline("Deadline", LocalDate.parse("2019-05-10").atStartOfDay());
        deadline.setBy(LocalDate.parse("2019-06-22").atStartOfDay());
        assertEquals(deadline.by, LocalDate.parse("2019-06-22").atStartOfDay());
    }

    @Test
    void isAllDay_true_success() {
        Event event = new Event("Event", LocalDate.parse("2019-05-10").atStartOfDay(), true);
        assertEquals(event.isAllDay(), true);
    }

    @Test
    void setAllDay() {
        Deadline deadline = new Deadline("Event", LocalDate.parse("2019-05-10").atStartOfDay());
        deadline.setAllDay(true);
        assertEquals(deadline.isAllDay, true);
    }

    @Test
    void testToString_byDateOnly_success() {
        assertEquals("[D][x] Achieve Goal A (at: May 10, 2019)",
                new Deadline("Achieve Goal A",
                        LocalDate.parse("2019-05-10").atStartOfDay(), true).toString());
    }

    @Test
    void testToString_byDateTime_success() {
        assertEquals("[D][x] Achieve Goal A (at: May 10, 2019 11:00:00 AM)",
                new Deadline("Achieve Goal A",
                        LocalDateTime.parse("2019-05-10T11:00")).toString());
    }
}