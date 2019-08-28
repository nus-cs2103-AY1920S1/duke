import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void correctInputTest(){
        assertEquals(true, Parser.correctInput("bye"));
    }

    @Test
    public void getDescTest() {
        String event = ("event project meeting /at 2/12/2019 1800");
        String[] eventArr = event.split(" ");

        assertEquals("project meeting /at 2/12/2019 1800", Parser.getDesc(eventArr));
    }
}