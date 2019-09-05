import duke.Parser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void testChangeToDateTimeFormatMethod() {
        String myTestTime = "1/10/2019 1600";
        Assertions.assertEquals(Parser.changeToDateTimeFormat(myTestTime).toString(), "2019-10-01T16:00",
                "duke.Parser.changeToDateTimeFormat() does not give the same result");
    }
}
