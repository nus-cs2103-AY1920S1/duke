package duke.command;

import duke.TestUtils;
import duke.task.TaskList;
import duke.task.TaskStub;
import duke.ui.MainWindowStub;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FindCommandTest {
    @Test
    void execute_randomSearchPattern_patternFound() {
        String findCommandPrefix = "find ";

        String[] testCases = new String[100];
        TaskList testTaskList = new TaskList();
        IntStream.rangeClosed(0, 99)
                .forEach(maxChar -> {
                    testCases[maxChar] = TestUtils.generateRandomString(2, maxChar);
                    testTaskList.addTask(new TaskStub(testCases[maxChar], ""));
                });

        Arrays.stream(testCases)
                .forEach(description -> {
                    MainWindowStub windowStub = new MainWindowStub();
                    FindCommand findCommand =
                            new FindCommand(findCommandPrefix + description, new String[0]);
                    assertDoesNotThrow(
                            () -> findCommand.execute(testTaskList, windowStub, null));
                    assertTrue(windowStub.getMessages().contains(description));
                });
    }

}
