import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.io.TempDir;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

abstract class CommandTest {
    private final OutputStream mockedSysOut = new ByteArrayOutputStream();
    private final OutputStream realSysOut = System.out;
    protected Storage store;
    protected Ui ui;
    protected Command command;
    private Path storeOutputPath;

    @BeforeEach
    void setUp(@TempDir Path tempDir) {
        storeOutputPath = tempDir.resolve("output.txt");
        store = new Storage(storeOutputPath);

        System.setOut(new PrintStream(mockedSysOut));
        ui = new Ui();
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(storeOutputPath);
        System.setOut(new PrintStream(realSysOut));
    }

    protected String getSysOutContents() {
        return mockedSysOut.toString();
    }

    protected String getStoreFileContents() {
        try {
            return Files.readString(storeOutputPath);
        } catch (IOException e) {
            return null;
        }
    }

    protected void assertFileContents(String expected) {
        Assertions.assertEquals(expected, getStoreFileContents());
    }

    protected void assertStdOutContents(String expected) {
        Assertions.assertEquals(expected, getSysOutContents());
    }

    protected void assertExit(boolean expected) {
        Assertions.assertEquals(expected, command.isExit());
    }

    protected List<Task> mutableTaskListOf(Task... tasks) {
        return new ArrayList<>(Arrays.asList(tasks));
    }
}