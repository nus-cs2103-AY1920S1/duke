import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void parse() {
        Parser parser = new Parser();
        CommandType todo = parser.parse("todo lab assignment");
        CommandType deadline = parser.parse("deadline lab assignment /by 19/09/2019 0000");
        CommandType event = parser.parse("event trip to taiwan /at 20/10/2019 1200");
        CommandType list = parser.parse("list");
        CommandType done = parser.parse("done 1");
        CommandType delete = parser.parse("delete 1");
        CommandType bye = parser.parse("bye");

        if (!todo.equals(CommandType.TODO)) ||
            !deadline.equals(CommandType.DEADLINE)) ||
            !event.equals(CommandType.EVENT)) ||
            !list.equals(CommandType.LIST)) ||
            !done.equals(CommandType.DONE)) ||
            !delete.equals(CommandType.DELETE)) ||
            !bye.equals(CommandType.BYE))) {
            fail();
        }
    }
}