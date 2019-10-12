import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {
    
    @Test
    void testToString() {
        Deadline deadline;
        try {
            deadline = new Deadline("borrow book", "28/09/2019 19:00");
            assertEquals("[D][\u2718] borrow book (by: 28/09/2019 19:00)", deadline.toString());
        } catch (DukeException e) {
            
        }
    }
}