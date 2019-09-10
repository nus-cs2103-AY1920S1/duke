import org.junit.Test;
import static junit.framework.TestCase.assertEquals;

public class ParserTest {
    Parser parser = new Parser;
    @Test
    public void isNumericTest(){
        assertEquals(true, Parser.isNumeric("1"));
    }
}
