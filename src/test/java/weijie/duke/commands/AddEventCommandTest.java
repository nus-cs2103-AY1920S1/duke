package weijie.duke.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import weijie.duke.models.Event;
import weijie.duke.models.Task;
import weijie.duke.repos.IRepository;
import weijie.duke.responses.TaskResponse;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AddEventCommandTest {
    private IRepository<Task> repo;

    @BeforeEach
    void setup() {
        repo = new MockTaskRepo();
    }

    @Test
    void execute_oneValidInput_taskAddedAndReturnedValidResponse() {
        String[] args = "event eventful /at 06/12/2018 2359".split(" ");

        AddEventCommand command = new AddEventCommand(repo);
        TaskResponse response = command.execute(args);

        Task taskCreated = new Event("eventful",
                LocalDateTime.of(2018, 12, 6, 23, 59));

        List<Task> expectedTasksList = Collections.singletonList(taskCreated);

        assertFalse(response.isInvalidInput());
        assertIterableEquals(expectedTasksList, response.getTasks());
        assertIterableEquals(expectedTasksList, repo.getAll());
    }

    @Test
    void execute_invalidDateInput_noTaskAddedAndReturnsInvalidInputResponse() {
        String[] args = "event help /at 23/13/2019 1800".split(" ");

        AddEventCommand command = new AddEventCommand(repo);
        TaskResponse response = command.execute(args);

        assertTrue(response.isInvalidInput());
        assertTrue(repo.getAll().isEmpty());
    }

    @Test
    void execute_inputWithNoDateAndTimeSpecified_noTaskAddedAndReturnsInvalidInputResponse() {
        String[] args = "event orbital splashdown :O".split(" ");

        AddEventCommand command = new AddEventCommand(repo);
        TaskResponse response = command.execute(args);

        assertTrue(response.isInvalidInput());
        assertTrue(repo.getAll().isEmpty());
    }

}
