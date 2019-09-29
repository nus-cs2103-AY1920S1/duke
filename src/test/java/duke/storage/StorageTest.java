package duke.storage;

import duke.task.TaskList;
import duke.ui.MainWindowStub;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class StorageTest {
    //randomly generated uuid to ensure it is conflict free
    private static final String TEST_DIR_NAME = "c1834a65-3269-45d1-815b-3ae7130a6980";
    private static final String TRUNCATED_TEST_DIR =
            TEST_DIR_NAME.substring(0, TEST_DIR_NAME.length() - 1);
    private static String originalWorkingDirectory;
    @TempDir
    static Path testTempDir;

    @BeforeAll
    static void setUpTestDirectory() {
        originalWorkingDirectory = System.getProperty("user.dir");

        try {
            Path path = testTempDir;
            System.out.println(path.toString());

            for (int i = 0; i <= 3; i++) {
                path = path.resolve(TRUNCATED_TEST_DIR + i);
                Files.createDirectory(path);
            }

            System.setProperty("user.dir", path.toString());
        } catch (IOException ex) {
            fail(ex.getMessage());
        }
    }

    @Test
    void setFilePath_dirExists_dirNotCreated() {
        assertDoesNotThrow(() -> new Storage(TEST_DIR_NAME, null));
        Path dirCreated = Paths.get(System.getProperty("user.dir"), TEST_DIR_NAME);

        if (Files.isDirectory(dirCreated)) {
            fail("Fallback directory created when not required by Storage.");
        }
    }

    @Test
    void setFilePath_fallbackDirNotExists_dirCreated() {
        final String FALLBACK_DIR_NAME = ((Double) Math.random()).toString();
        MainWindowStub uiStub = new MainWindowStub();
        assertDoesNotThrow(() -> new Storage(FALLBACK_DIR_NAME, uiStub));
        Path dirCreated = Paths.get(System.getProperty("user.dir"), FALLBACK_DIR_NAME);

        if (!Files.isDirectory(dirCreated)) {
            fail("Fallback directory creation for Storage failed.");
        }
    }

    @Test
    void setFilePath_fallbackDirNameInvalid_dirCreationFail() {
        final String INVALID_DIR_NAME = Paths.get("anInvalid","dirName").toString();
        MainWindowStub outputUi = new MainWindowStub();
        new Storage(INVALID_DIR_NAME, outputUi);

        assertTrue(outputUi.getMessages().contains(
                String.format(" =X  Oops! I failed to find a %s directory upwards\n", INVALID_DIR_NAME)));
    }

    @Test
    void loadTasksToList_validatedTaskCases_loadSuccess() {
        TaskList listOfTasks = new TaskList(); // implementation is simple enough.
        final String TEST_TASK_CASE_1 =
                "{ type: deadline, done: false, description: 2103ip, time: 01/12/1997 0000 }";
        final String TEST_TASK_CASE_2 = "{ type: todo, done: true, description: --129n8sk }";

        try {
            File testFile = Files.createFile(
                    Paths.get(
                        testTempDir.toString(),
                        TEST_DIR_NAME,
                        "taskData.txt"))
                    .toFile();

            FileWriter fileWriter = new FileWriter(testFile);

            fileWriter.write(TEST_TASK_CASE_1 + "\n");
            fileWriter.write(TEST_TASK_CASE_2);
            fileWriter.close();

            Storage storage = new Storage(TEST_DIR_NAME, null);
            storage.loadTasksToList(listOfTasks);

            assertEquals(2, listOfTasks.getSize());
        } catch (IOException ex) {
            fail("IOException encountered when trying to create loadTasksToList test files.\n"
                    + ex.getMessage());
        }
    }

    @Test
    void loadTasksToList_invalidTaskCases_uiErrorMsg() {
        TaskList listOfTasks = new TaskList(); // implementation is simple enough.
        MainWindowStub outputUi = new MainWindowStub();

        final String TEST_TASK_CASE_INVALID_ARGUMENT =
                "{ type: unknown, done: false, description: 2103ip, time: 01/12/1997 0000 }";

        try {
            File testFile = Files.createFile(
                    Paths.get(
                            testTempDir.toString(),
                            TEST_DIR_NAME,
                            TRUNCATED_TEST_DIR + "1",
                            "taskData.txt"))
                    .toFile();

            FileWriter fileWriter = new FileWriter(testFile);

            fileWriter.write(TEST_TASK_CASE_INVALID_ARGUMENT);
            fileWriter.close();


            Storage storage = new Storage(TRUNCATED_TEST_DIR + "1", outputUi);
            storage.loadTasksToList(listOfTasks);

            assertEquals(0, listOfTasks.getSize());
            assertTrue(outputUi.getMessages().contains(
                    " =X  Oops! I encountered an invalid task type value while\n"));
        } catch (IOException ex) {
            fail("IOException encountered when trying to create loadTasksToList test files.\n"
                    + ex.getMessage());
        }
    }

}
