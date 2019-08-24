import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void testTodo() {
        final Duke d = new Duke();
        String border = "-------------------------------------";

        StringBuilder sb = new StringBuilder();
        sb.append(border + "\n");
        sb.append("Got it. I've added this task: \n");
        sb.append("[T][\u2718] test\n");
        sb.append("Now you have " + 1 + " tasks in the list.\n");
        sb.append(border + "\n");
        String correct = sb.toString();
        sb.setLength(0);

        assertEquals(correct, d.generateTask("todo test"));

        sb.append(border + "\n");
        sb.append("Todo must have valid description\n");
        sb.append(border + "\n");
        String wrong = sb.toString();

        assertEquals(wrong, d.generateTask("todo"));
    }
}
