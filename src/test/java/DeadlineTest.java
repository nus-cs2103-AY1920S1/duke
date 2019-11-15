import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for Deadline.
 */
public class DeadlineTest {
    /**
     * Creates a new Deadline task, and checks whether its toString() method gives the correct output.
     */
    @Test
    public void createTaskTest() {
        try {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
            Deadline newDeadline = new Deadline("Sample Deadline",
                    true,
                    dateFormatter.parse("2/12/2019 1800"));
            assertEquals("[D][\u2713] Sample Deadline (by: Mon Dec 02 18:00:00 SGT 2019)",
                    newDeadline.toString());
        } catch (ParseException e) {
        }
    }
}