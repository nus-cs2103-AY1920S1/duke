import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void testTodo() {
        Duke d = new Duke();
        String border = "-------------------------------------";

        StringBuilder sb = new StringBuilder();
        sb.append(border + "\n");
        sb.append("Got it. I've added this task: \n");
        sb.append("[T][\u2718] test\n");
        sb.append("Now you have " + 1 + " tasks in the list.\n");
        sb.append(border + "\n");
        String correct = sb.toString();
        sb.setLength(0);

        sb.append(border + "\n");
        sb.append("Todo must have valid description\n");
        sb.append(border + "\n");
        String wrong = sb.toString();

        assertEquals(correct, d.generateTask("todo test"));
        assertEquals(wrong, d.generateTask("todo"));
    }
}
