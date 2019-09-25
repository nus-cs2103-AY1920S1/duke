package weijie.duke.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import weijie.duke.models.Deadline;
import weijie.duke.models.Task;
import weijie.duke.repos.IRepository;
import weijie.duke.responses.TaskResponse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class AddDeadlineCommandTest {

    private IRepository<Task> repo;

    @BeforeEach
    void setup() {
        repo = new MockTaskRepo();
    }

    @Test
    void execute_oneValidInput_taskAddedAndReturnedValidResponse() {
        String[] args = "deadline thing /by 23/4/2019 1800".split(" ");

        AddDeadlineCommand command = new AddDeadlineCommand(repo);
        TaskResponse response = command.execute(args);

        Task taskCreated = new Deadline("thing",
                LocalDateTime.of(2019, 4, 23, 18, 0));

        List<Task> expectedTasksList = Collections.singletonList(taskCreated);

        assertFalse(response.isInvalidInput());
        assertIterableEquals(expectedTasksList, response.getTasks());
        assertIterableEquals(expectedTasksList, repo.getAll());
    }

    @Test
    void execute_twoValidInputs_tasksAddedAndReturnedValidResponse() {
        String[] args1 = "deadline thingy /by 12/6/2019 1900".split(" ");
        String[] args2 = "deadline other thing /by 14/7/2019 1234".split(" ");

        final AddDeadlineCommand command = new AddDeadlineCommand(repo);
        final TaskResponse response1 = command.execute(args1);
        final TaskResponse response2 = command.execute(args2);

        Task task1 = new Deadline("thingy",
                LocalDateTime.of(2019, 6, 12, 19, 0));
        Task task2 = new Deadline("other thing",
                LocalDateTime.of(2019, 7, 14, 12, 34));

        List<Task> expectedTasksList = new ArrayList<>();
        expectedTasksList.add(task1);
        expectedTasksList.add(task2);

        assertFalse(response1.isInvalidInput());
        assertFalse(response2.isInvalidInput());
        assertIterableEquals(Collections.singletonList(task1), response1.getTasks());
        assertIterableEquals(Collections.singletonList(task2), response2.getTasks());
        assertIterableEquals(expectedTasksList, repo.getAll());
    }

    @Test
    void execute_invalidDateFormat_noTaskAddedAndReturnsInvalidInputResponse() {
        String[] args = "deadline thing /by 23/4/2019, 1800".split(" ");

        AddDeadlineCommand command = new AddDeadlineCommand(repo);
        TaskResponse response = command.execute(args);


        assertTrue(response.isInvalidInput());
        assertTrue(repo.getAll().isEmpty());
    }

    @Test
    void execute_inputWithNoDateAndTimeSpecified_noTaskAddedAndReturnsInvalidInputResponse() {
        String[] args = "deadline very busy help me".split(" ");

        AddDeadlineCommand command = new AddDeadlineCommand(repo);
        TaskResponse response = command.execute(args);

        assertTrue(response.isInvalidInput());
        assertTrue(repo.getAll().isEmpty());
    }
}
