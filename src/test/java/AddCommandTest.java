import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.fail;

public class AddCommandTest {

    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    @BeforeEach
    void init() {
        storage = new Storage("");
        taskList = new TaskList();
    }

    @ParameterizedTest
    @CsvSource({"todo, dance", "deadline, homework /by 01/09/2019 2359", "event, party /at NUS"})
    void testExecute(String cmd, String task) {
        String input = task;
        InputStream in = new ByteArrayInputStream((input.getBytes()));
        System.setIn(in);
        ui = new Ui();
        try {
            AddCommand ac = new AddCommand(cmd);
            ac.execute(storage, taskList, ui);
        } catch (DukeException e) {
            fail("failed to add item into list");
        }
    }
}
