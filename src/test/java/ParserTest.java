import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void testChangeToDateTimeFormatMethod() {
        String myTestTime = "1/10/2019 1600";
        Assertions.assertEquals(Parser.changeToDateTimeFormat(myTestTime).toString(), "2019-10-01T16:00",
                "Parser.changeToDateTimeFormat() does not give the same result");
    }

    @Test
    public void testGetFirstWordMethod() {
        String myTestString = "hello my name is francis";
        Assertions.assertEquals(Parser.getFirstWord(myTestString), "hello",
                "getFirstWord method does not give the same result");
    }

    @Test
    public void testExcludeFirstWord() {
        String myTestString = "hello my name is francis";
        Assertions.assertEquals(Parser.excludeFirstWord(myTestString, "hello"), "my name is francis",
                "excludeFirstWord method does not give the same result");
    }
}
