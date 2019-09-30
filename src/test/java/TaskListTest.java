import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void addTaskSuccessfully() throws DukeException {

        assertEquals("[T][\u2718] DESCRIPTION",  Parser.parsesAdd("todo DESCRIPTION", TaskType.TODO).toString());

        assertEquals("[D][\u2718] DESCRIPTION (by: DATE)",
                Parser.parsesAdd("deadline DESCRIPTION /by DATE", TaskType.DEADLINE).toString());

        assertEquals("[E][\u2718] DESCRIPTION (at: DATE)",  Parser.parsesAdd("event DESCRIPTION /at DATE", TaskType.EVENT).toString());
    }

    @Test
    public void addTaskUnsuccessfully() throws DukeException {

        try {
            Parser.parsesAdd("todo", TaskType.TODO).toString();
        } catch (DukeException e) {
            assertEquals(DukeException.emptyDescription().getMessage(), e.getMessage());
        }

        try {
            Parser.parsesAdd("", TaskType.TODO).toString();
        } catch (DukeException e) {
            assertEquals(DukeException.emptyDescription().getMessage(), e.getMessage());
        }


        try {
            Parser.parsesAdd("deadline", TaskType.DEADLINE).toString();
        } catch (DukeException e) {
            assertEquals(DukeException.emptyDescription().getMessage(), e.getMessage());
        }

        try {
            Parser.parsesAdd("", TaskType.DEADLINE).toString();
        } catch (DukeException e) {
            assertEquals(DukeException.emptyDescription().getMessage(), e.getMessage());
        }


        try {
            Parser.parsesAdd("event", TaskType.EVENT).toString();
        } catch (DukeException e) {
            assertEquals(DukeException.emptyDescription().getMessage(), e.getMessage());
        }

        try {
            Parser.parsesAdd("", TaskType.EVENT).toString();
        } catch (DukeException e) {
            assertEquals(DukeException.emptyDescription().getMessage(), e.getMessage());
        }

    }



}
