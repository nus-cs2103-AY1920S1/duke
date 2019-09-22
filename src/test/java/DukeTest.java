import duke.Duke;
import org.junit.jupiter.api.Test;

public class DukeTest {
    private final Duke duke = new Duke();

    @Test
    public void checkDukeHelp() {
        assert(duke.run("help") != null):"Wrong content of help information";
    }
    public static void main(String[] args) {
            System.out.println("Nothing to test");
    }
}
