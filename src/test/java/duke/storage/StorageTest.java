package duke.storage;

import duke.task.TaskList;
import duke.ui.UiStub;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class StorageTest {
    //randomly generated uuid to ensure it is conflict free
    private static final String TEST_DIR_NAME = "c1834a65-3269-45d1-815b-3ae7130a6988";
    private static String originalWorkingDirectory;

    @BeforeAll
    static void setUpTestDirectory() {
        originalWorkingDirectory = System.getProperty("user.dir");

        try {
            Path path = Paths.get(originalWorkingDirectory, TEST_DIR_NAME);
            Files.createDirectory(path);

            for (int i = 1; i <= 3; i++) {
                path = Paths.get(path.toString(), TEST_DIR_NAME + i);
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
        assertDoesNotThrow(() -> new Storage(FALLBACK_DIR_NAME, null));
        Path dirCreated = Paths.get(System.getProperty("user.dir"), FALLBACK_DIR_NAME);

        if (!Files.isDirectory(dirCreated)) {
            fail("Fallback directory creation for Storage failed.");
        }
    }

    @Test
    void setFilePath_fallbackDirNameInvalid_dirCreationFail() {
        final String INVALID_DIR_NAME = Paths.get("anInvalid","dirName").toString();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        UiStub outputUi = new UiStub(new PrintStream(outputStream));
        new Storage(INVALID_DIR_NAME, outputUi);

        Scanner uiOutputScanner = new Scanner(new ByteArrayInputStream(outputStream.toByteArray()));
        StringBuilder uiOutput = new StringBuilder();

        while (uiOutputScanner.hasNextLine()) {
            uiOutput.append(uiOutputScanner.nextLine());
            uiOutput.append('\n');
        }
        uiOutputScanner.close();

        assertTrue(uiOutput.toString().contains(
                String.format(" ☹  Oops! I failed to find a %s directory upwards\n", INVALID_DIR_NAME)));
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
                        originalWorkingDirectory,
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
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        UiStub outputUi = new UiStub(new PrintStream(outputStream));

        final String TEST_TASK_CASE_INVALID_ARGUMENT =
                "{ type: unknown, done: false, description: 2103ip, time: 01/12/1997 0000 }";

        try {
            File testFile = Files.createFile(
                    Paths.get(
                            originalWorkingDirectory,
                            TEST_DIR_NAME,
                            TEST_DIR_NAME + "1",
                            "taskData.txt"))
                    .toFile();

            FileWriter fileWriter = new FileWriter(testFile);

            fileWriter.write(TEST_TASK_CASE_INVALID_ARGUMENT);
            fileWriter.close();

            Storage storage = new Storage(TEST_DIR_NAME + "1", outputUi);
            storage.loadTasksToList(listOfTasks);

            Scanner uiOutputScanner = new Scanner(new ByteArrayInputStream(outputStream.toByteArray()));
            StringBuilder uiOutput = new StringBuilder();

            while (uiOutputScanner.hasNextLine()) {
                uiOutput.append(uiOutputScanner.nextLine());
                uiOutput.append('\n');
            }
            uiOutputScanner.close();

            assertEquals(0, listOfTasks.getSize());
            assertTrue(uiOutput.toString().contains(
                    " ☹  Oops! I encountered an invalid task type value while\n"));
        } catch (IOException ex) {
            fail("IOException encountered when trying to create loadTasksToList test files.\n"
                    + ex.getMessage());
        }
    }

    @AfterAll
    static void cleanUpTestDirectory() {
        System.setProperty("user.dir", originalWorkingDirectory);
        try {
            Files.walk(Paths.get(originalWorkingDirectory, TEST_DIR_NAME))
                    .sorted(Comparator.reverseOrder())
                    .forEach(
                            pathToDelete -> {
                                try {
                                    Files.deleteIfExists(pathToDelete);
                                } catch (IOException ex) {
                                    fail("Failed to clean test directory for StorageTest."
                                            + ex.getMessage());
                                }
                            });
        } catch (IOException ex) {
            fail("Failed to clean test directory for StorageTest.\n"
                    + ex.getMessage());
        }
    }
}
