import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

        /*
        void testingGetLabel() {
            assertEquals("T", new ToDo("check").getLabel());
            assertEquals("D", new Deadline("check", "today").getLabel());
            assertEquals("E", new Event("check", "today").getLabel());
        }

        @org.junit.jupiter.api.Test
        void testingGetTimeLabel() {
            assertEquals("", new ToDo("check").getTimeLabel());
            assertEquals("today", new Deadline("check", "today").getTimeLabel());
            assertEquals("day after", new Event("check", "day after").getTimeLabel());
        }

         */

        @org.junit.jupiter.api.Test
        void testingGetInfo() {
            Deadline newDead = new Deadline("check", "today");
            newDead.mark();
            assertEquals(0, new Deadline("check", "today").getInfo());
            assertEquals(1, newDead.getInfo());
        }

        @org.junit.jupiter.api.Test
        void testingGetDescription() {
            assertEquals("check", new ToDo("check").getDescription());
            assertEquals("check", new Deadline("check", "day after").getDescription());
            assertEquals("check", new Event("check", "tomorrow").getDescription());
        }

        @org.junit.jupiter.api.Test
        void testingMark() {
            Deadline newDead = new Deadline("check", "today");
            assertEquals(0, newDead.getInfo());
            newDead.mark();
            assertEquals(1, newDead.getInfo());
        }

        @org.junit.jupiter.api.Test
        void testingGetStatusIcon() {
            Deadline newDead = new Deadline("check", "today");
            newDead.mark();
            assertEquals("[\u2718] ", new Deadline("check", "today").getStatusIcon());
            assertEquals("[\u2713] ", newDead.getStatusIcon());
        }

        @org.junit.jupiter.api.Test
        void testingToString() {
            assertEquals("[T][\u2718] check", new ToDo("check").toString());
            assertEquals("[D][\u2718] check (by: today)", new Deadline("check", "today").toString());
            assertEquals("[E][\u2718] check (at: today)", new Event("check", "today").toString());
        }
    }
