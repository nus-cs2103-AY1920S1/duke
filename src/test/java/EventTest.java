import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void testEvent() {
        final Duke d = new Duke();
        String border = "-------------------------------------";

        StringBuilder sb = new StringBuilder();
        sb.append(border + "\n");
        sb.append("Got it. I've added this task: \n");
        sb.append("[E][\u2718] test  (at: 12/12/1212 18:00 - 19:00)\n");
        sb.append("Now you have " + 1 + " tasks in the list.\n");
        sb.append(border + "\n");
        String correct = sb.toString();
        sb.setLength(0);

        assertEquals(correct, d.generateTask("event test /at 12/12/1212 18:00 - 19:00"));

        sb.append(border + "\n");
        sb.append("Invalid Event's arguments \n");
        sb.append(border + "\n");
        String wrong = sb.toString();

        assertEquals(wrong, d.generateTask("event test /at 12/12/1212 1800 - 1900"));
        assertEquals(wrong, d.generateTask("event test /at 12/12/212 25:00"));
        assertEquals(wrong, d.generateTask("event test /at 12/12/1212 2pm"));
        assertEquals(wrong, d.generateTask("event test /at 18:00 12/12/1212"));

    }
}
