import task.Deadline;
import task.Event;
import task.ToDo;

import org.junit.jupiter.api.Test;

import misc.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {
    @Test
    void testConvertTaskToText() {
        Parser parser = new Parser();

        Event doneEvent = new Event("project", "10/12/19 1000-1400", true);
        Event undoneEvent = new Event("project", "10/12/19 1000-1400", false);

        Deadline doneDeadline = new Deadline("homework", "10/12/19 2359", true);
        Deadline undoneDeadline = new Deadline("homework", "24/07/19 1000", false);

        ToDo doneToDo = new ToDo("join club", true);
        ToDo undoneToDo = new ToDo("join club", false);

        assertEquals("E : 1 : project : 10/12/19 1000-1400", parser.writeTaskAsText(doneEvent));
        assertEquals("E : 0 : project : 10/12/19 1000-1400", parser.writeTaskAsText(undoneEvent));

        assertEquals("D : 1 : homework : 10/12/19 2359", parser.writeTaskAsText(doneDeadline));
        assertEquals("D : 0 : homework : 24/07/19 1000", parser.writeTaskAsText(undoneDeadline));

        assertEquals("T : 1 : join club", parser.writeTaskAsText(doneToDo));
        assertEquals("T : 0 : join club", parser.writeTaskAsText(undoneToDo));
    }

    @Test
    void testConvertStringToTime() {
        Parser parser = new Parser();

        String dateTimeEvent1 = "08/12/19 1359-1700";
        String dateTimeEvent2 = "08/12/19 0810-1345";

        String dateTimeDeadline1 = "23/07/19 0700";
        String dateTimeDeadline2 = "13/02/78 2359";

        assertEquals("08 Dec 2019 01:59PM to 05:00PM",
                parser.convertStringToTime(dateTimeEvent1, "event"));
        assertEquals("08 Dec 2019 08:10AM to 01:45PM",
                parser.convertStringToTime(dateTimeEvent2, "event"));

        assertEquals("23 Jul 2019 07:00AM",
                parser.convertStringToTime(dateTimeDeadline1, "deadline"));
        assertEquals("13 Feb 2078 11:59PM",
                parser.convertStringToTime(dateTimeDeadline2, "deadline"));
    }
}