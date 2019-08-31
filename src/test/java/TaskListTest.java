import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Represents a TaskListJUnitTesting which tests TaskList.
 * @author Ang Kai Qi
 * @version 0.1.3
 */
public class TaskListTest {

    /**
     * Tests addTask of TaskList object with successful todo, deadline, event, bye and unknown inputs.
     */
    @Test
    public void testAddTask() {
        String todo = "todo borrow book";
        String resTodo = "Got it. I've added this task:\n  [T][\u2718] borrow book\nNow you have 1 tasks in the list.";
        assertEquals(resTodo, new TaskList(new Storage("C:\\Users\\AngKa\\duke\\src\\main\\java\\test.txt"))
                        .addTask(todo));

        Task.decCurrTotal();
        String deadline = "deadline return book /by 18/8/2019 2000";
        String resDeadline = "Got it. I've added this task:\n  [D][\u2718] return book (by: 18/8/2019 2000)\nNow you "
                               + "have 1 tasks in the list.";
        assertEquals(resDeadline, new TaskList(new Storage("C:\\Users\\AngKa\\duke\\src\\main\\java\\test.txt"))
                        .addTask(deadline));

        Task.decCurrTotal();
        String event = "event reading circle /at 18/8/8/2019 1700-1900";
        String resEvent = "Got it. I've added this task:\n  [E][\u2718] reading circle (at: 18/8/8/2019 1700-1900)\n"
                            + "Now you have 1 tasks in the list.";
        assertEquals(resEvent, new TaskList(new Storage("C:\\Users\\AngKa\\duke\\src\\main\\java\\test.txt"))
                .addTask(event));

        String bye = "bye";
        String resBye = "bye";
        assertEquals(resBye, new TaskList(new Storage("C:\\Users\\AngKa\\duke\\src\\main\\java\\test.txt"))
                        .addTask(bye));

        String unknown = "unknown";
        String resUnknown = "\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(";
        assertEquals(resUnknown, new TaskList(new Storage("C:\\Users\\AngKa\\duke\\src\\main\\java\\test.txt"))
                        .addTask(unknown));
    }
}
