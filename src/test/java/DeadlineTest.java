import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class DeadlineTest {

    @Test
    public void testDeadline() {
        String border = "-------------------------------------";
        final Duke d = new Duke();

        StringBuilder sb = new StringBuilder();
        sb.append(border + "\n");
        sb.append("Got it. I've added this task: \n");
        sb.append("[D][\u2718] test  (by: 12/12/1212 18:00)\n");
        sb.append("Now you have 1 tasks in the list.\n");
        sb.append(border + "\n");
        String correct = sb.toString();
        sb.setLength(0);

        assertEquals(correct, d.generateTask("deadline test /by 12/12/1212 18:00"));

        sb.append(border + "\n");
        sb.append("Invalid Deadline's arguments \n");
        sb.append(border + "\n");
        String wrong = sb.toString();

        assertEquals(wrong, d.generateTask("deadline test /by 12/12/1212 1800"));
        assertEquals(wrong, d.generateTask("deadline test /by 12/12/1212 18/00"));
        assertEquals(wrong, d.generateTask("deadline test /by 12-1-212 25:00"));
        assertEquals(wrong, d.generateTask("deadline test /by 12/12/1212 2pm"));
        assertEquals(wrong, d.generateTask("deadline test /by 18:00 12/12/1212"));


    }
}
