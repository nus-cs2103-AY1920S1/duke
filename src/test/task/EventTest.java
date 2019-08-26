package task;

import exception.DukeIllegalArgumentException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void getStartDateTime_sameDateTime_success() {
        Event event = new Event("Event", LocalDate.parse("2019-05-10").atStartOfDay());
        assertEquals(event.getStartDateTime(), LocalDate.parse("2019-05-10").atStartOfDay());
    }

    @Test
    void setStartDateTime_sameDateTime_success() throws DukeIllegalArgumentException {
        Event event = new Event("Event", LocalDate.parse("2019-05-10").atStartOfDay());
        event.setStartDateTime(LocalDate.parse("2019-06-22").atStartOfDay());
        assertEquals(event.startDateTime, LocalDate.parse("2019-06-22").atStartOfDay());
    }

    @Test
    void getEndDateTime_sameDateTime_success() {
        Event event = new Event("Event", LocalDate.parse("2019-05-10").atStartOfDay());
        assertEquals(event.getEndDateTime(), LocalDate.parse("2019-05-10").atStartOfDay());
    }

    @Test
    void setEndDateTime_sameDateTime_success() throws DukeIllegalArgumentException {
        Event event = new Event("Event", LocalDate.parse("2019-05-10").atStartOfDay());
        event.setEndDateTime(LocalDate.parse("2019-06-22").atStartOfDay());
        assertEquals(event.endDateTime, LocalDate.parse("2019-06-22").atStartOfDay());
    }

    @Test
    void isAllDay_true_success() {
        Event event = new Event("Event", LocalDate.parse("2019-05-10").atStartOfDay(), true);
        assertEquals(event.isAllDay(), true);
    }

    @Test
    void setAllDay_true_success() {
        Event event = new Event("Event", LocalDate.parse("2019-05-10").atStartOfDay());
        event.setAllDay(true);
        assertEquals(event.isAllDay, true);
    }

    @Test
    void testToString_startDateOnly_success() {
        assertEquals("[E][x] Mother's Day (at: May 10, 2019)",
                new Event("Mother's Day", LocalDate.parse("2019-05-10").atStartOfDay(), true).toString());
    }

    @Test
    void testToString_startDateTimeOnly_success() {
        assertEquals("[E][x] Start of Long Project (at: May 10, 2019, 11:00:00 AM)",
                new Event("Start of Long Project", LocalDateTime.parse("2019-05-10T11:00")).toString());
    }

    @Test
    void testToString_startEndDate_success() throws DukeIllegalArgumentException {
        assertEquals("[E][x] Conference (at: May 10, 2019 - May 11, 2019)",
                new Event("Conference",
                        LocalDate.parse("2019-05-10").atStartOfDay(),
                        LocalDate.parse("2019-05-11").atStartOfDay(),
                        true).toString());
    }

    @Test
    void testToString_startEndDateTime_success() throws DukeIllegalArgumentException {
        assertEquals("[E][x] Hackathon (at: May 10, 2019, 10:00:00 AM - May 11, 2019, 1:00:00 PM)",
                new Event("Hackathon",
                        LocalDateTime.parse("2019-05-10T10:00"),
                        LocalDateTime.parse("2019-05-11T13:00")).toString());
    }

    @Test
    void testToString_startEndDateTimeSame_success() throws DukeIllegalArgumentException {
        assertEquals("[E][x] Meeting (at: May 10, 2019, 10:00:00 AM - 1:00:00 PM)",
                new Event("Meeting",
                        LocalDateTime.parse("2019-05-10T10:00"),
                        LocalDateTime.parse("2019-05-10T13:00")).toString());
    }

    @Test
    void testToString_endBeforeStart_exceptionThrown() {
        try {
            assertEquals("[E][x] Meeting (at: May 10, 2019, 10:00:00 AM - 1:00:00 PM)",
                    new Event("Meeting",
                            LocalDateTime.parse("2019-05-10T10:00"),
                            LocalDateTime.parse("2019-05-10T13:00")).toString());
        } catch (DukeIllegalArgumentException e) {
            assertEquals("End date should be before start date!", e.getMessage());
        }
    }
}