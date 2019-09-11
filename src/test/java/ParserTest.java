import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void correctInputTest(){
        Assertions.assertEquals(true, Parser.correctInput("bye"));
    }

    @Test
    public void getDescTest() {
        String event = ("event project meeting /at 2/12/2019 1800");
        String[] eventArr = event.split(" ");

        Assertions.assertEquals("project meeting /at 2/12/2019 1800", Parser.getDesc(eventArr));
    }
}