import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void getStatusIcon() {
    }

    @Test
    void setDone() {
    }

    @Test
    void testToString() {
        Task duck = new Task("Duck"); // Default constructor is set to string name and false
        assertEquals( "[\u2718] Duck", duck.toString()  );
    }
}