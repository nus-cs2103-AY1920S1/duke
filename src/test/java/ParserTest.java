
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class ParserTest {
    @Test
    public void dateInputTest1() {
        Assertions.assertThrows(DukeException.class, () -> {
            Parser.parse("deadline return book /by 29/02/2019 1800");
        });
    }


    @Test
    public void dateInputTest2() {
        Assertions.assertThrows(DukeException.class, () -> {
            Parser.parse("deadline return book /by 31/4/2019 1800");
        });
    }

    @Test
    public void dateInputTest3() {
        Assertions.assertThrows(DukeException.class, () -> {
            Parser.parse("deadline return book /by 31/13/2019 1800");
        });
    }

    @Test
    public void dateInputTest4() {
        Assertions.assertThrows(DukeException.class, () -> {
            Parser.parse("deadline return book /by when the cow jumps over the moon");
        });
    }

    @Test
    public void dateInputTest5() {
        Assertions.assertThrows(DukeException.class, () -> {
            Parser.parse("event return book /at when the cow jumps over the moon");
        });
    }


}
