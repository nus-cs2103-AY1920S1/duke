import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DukeTest {

    @Test
    public void CommandAddTodoTest(){
        String fullCommand = "todo project test";
        Task expected = new Todo(fullCommand.split(" ", 2)[1]);
        Task actual = new CommandAdd(fullCommand).getTask();
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void CommandAddDeadlineTest(){
        String fullCommand = "deadline homework /by 12/11/2019 1900";
        Task expected = new Deadline("homework", "12/11/2019 1900");
        Task actual = new CommandAdd(fullCommand).getTask();
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void CommandAddEventTest(){
        String fullCommand = "event company dinner /at 9/5/2019 1730";
        Task expected = new Event("company dinner", "9/5/2019 1730");
        Task actual = new CommandAdd(fullCommand).getTask();
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void ParserParseTest() throws DukeException {
        String fullCommand = "event company dinner /at 9/5/2019 1730";
        Command expected = new CommandAdd(fullCommand);
        Command actual = Parser.parse("event company dinner /at 9/5/2019 1730");
        assertEquals(expected.toString(), actual.toString());

    }
}