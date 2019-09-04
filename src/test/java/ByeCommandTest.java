import static org.junit.jupiter.api.Assertions.assertEquals;

public class ByeCommandTest {
    /**
     * Tests ByeCommand's IsExit method.
     */
    public void isExitTest() {
        assertEquals(new ByeCommand().isExit(), false);
    }
}
