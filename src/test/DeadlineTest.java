import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {

    @Test
    void toTextFile() {
        Deadline newDeadline = new Deadline("test the code", "tonight");
        assertEquals("D | 0 | test the code | tonight", newDeadline.toTextFile());
    }

    @Test
    void testToString() {
        Deadline newDeadline = new Deadline("I need to test the code", "tonight");
        assertEquals("[D]" + "[\u2718]" + " " + "I need to test the code (by: tonight)", newDeadline.toString());
    }

    @Test
    void getStatusIcon() {
        Deadline newDeadline = new Deadline("I need to test the code", "tonight");
        assertEquals("[\u2718]", newDeadline.getStatusIcon());
    }

    @Test
    void markAsDone() {
        Deadline newDeadline = new Deadline("I need to test the code", "tonight");
        newDeadline.markAsDone();
        assertEquals("[\u2713]", newDeadline.getStatusIcon());
    }

}