import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadlineTest {

    @Test
    void testToString() {
        Deadline dead = new Deadline("swim ", " 02 Dec 2019, 18:00 PM");
        assertEquals("[D][ ] swim (by: 02 Dec 2019, 18:00 PM)", dead.toString());
    }
}