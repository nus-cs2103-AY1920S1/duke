import static org.junit.jupiter.api.Assertions.assertEquals;

public class ByeCommandTest {
    public void isExitTest() {
        assertEquals(new ByeCommand().isExit(), false);
    }
}
