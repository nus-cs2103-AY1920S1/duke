import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void parse() {
        Parser parseTest = new Parser();
        InputType bye =  parseTest.parse("bye");
        InputType list =  parseTest.parse("list");
        InputType todo =  parseTest.parse("todo abc");
        InputType event =  parseTest.parse("event abc /at 9/12/2019 0000");
        InputType deadline =  parseTest.parse("deadline abc /at 9/12/2019 0000");
        InputType done =  parseTest.parse("done 1");
        InputType delete =  parseTest.parse("delete 1");

        if (!(bye.equals(InputType.BYE)) ||
            !(list.equals(InputType.LIST)) ||
            !(todo.equals(InputType.TODO)) ||
            !(event.equals(InputType.EVENT)) ||
            !(deadline.equals(InputType.DEADLINE)) ||
            !(done.equals(InputType.DONE)) ||
            !(delete.equals(InputType.DELETE))) {
            fail();
        }
    }
}