import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    /**
     * Tests Parser's parse function.
     */
    public void parseTest() {
        try {
            Parser.parse("blah");
            fail();
        } catch (Exception ex) {
            return;
        }
    }
}
