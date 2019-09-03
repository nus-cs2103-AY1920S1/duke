import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parseTest(){
        Command cmd1 = new CommandHi("");
        Command cmd2 = Parser.parse("hi");
        assertEquals(cmd1.toString(), cmd2.toString());

        Command cmd3 = new CommandDelete("4");
        Command cmd4 = Parser.parse("delete 4");
        assertEquals(cmd3.toString(), cmd4.toString());

        Command cmd5 = new CommandFind("fish");
        Command cmd6 = Parser.parse("find fish");
        assertEquals(cmd5.toString(), cmd6.toString());

        Command cmd7 = new CommandTodo("learn CS2103");
        Command cmd8 = Parser.parse("todo learn CS2103");
        assertEquals(cmd7.toString(), cmd8.toString());

        Command cmd9 = new IllegalCommand("to learn CS2103");
        Command cmd10 = Parser.parse("how to learn CS2103");
        assertEquals(cmd9.toString(), cmd10.toString());
    }
}