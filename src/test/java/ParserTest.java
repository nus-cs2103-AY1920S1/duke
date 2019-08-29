import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    public void parseTest() {
        try {
            Parser.parse("blah");
            fail();
        } catch (Exception ex) {
        }
    }
}
