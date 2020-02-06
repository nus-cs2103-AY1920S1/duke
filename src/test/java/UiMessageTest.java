import static org.junit.jupiter.api.Assertions.assertEquals;

import dose.util.UiMessage;
import org.junit.jupiter.api.Test;

public class UiMessageTest {
    @Test
    public void testWelcome() {
        UiMessage uiMessage = UiMessage.WELCOME;
        assertEquals("Hello! What can I do for you?",
            uiMessage.getMessage());
    }

    @Test
    public void testTaskStatus() {
        String uiMessageFront = UiMessage.TASKS_STATUS_FRONT.getMessage();
        int numTasks = 5;
        String uiMessageBack = UiMessage.TASKS_STATUS_BACK.getMessage();
        String message = uiMessageFront + numTasks + uiMessageBack;
        assertEquals("Now you have 5 items in this list.",
            message);
    }

    @Test
    public void testHelpMessage() {
        String helpMessage = UiMessage.getHelpMessage();
        assertEquals("Here are the things I can do...\n"
                + "Use todo to add a new todo.\n"
                +  "Use deadline /by [deadline] to add a new task with a deadline.\n"
                + "Use event /at [time] to add a new event at a time.\n"
                + "Use done [taskId] to mark a task as done.\n"
                + "Use delete [taskId] to remove a task from the list.\n"
                + "Use save to save your tasks to disk.\n"
                + "Use list to see all your tasks!\n",
            helpMessage);
    }
}
