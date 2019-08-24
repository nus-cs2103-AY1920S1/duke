import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class ExitCommandTest extends CommandTest {
    @Test
    void execute() {
        command = new ExitCommand();
        command.execute(new ArrayList<>(), ui, store);

        assertFileContents(null);
        assertStdOutContents("\t____________________________________________________________\n"
                + "\tBye. Hope to see you again soon!\n"
                + "\t____________________________________________________________\n");
        assertExit(true);
    }
}