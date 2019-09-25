package weijie.duke.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import weijie.duke.exceptions.DukeIoException;
import weijie.duke.models.Deadline;
import weijie.duke.models.Event;
import weijie.duke.models.Task;
import weijie.duke.models.Todo;
import weijie.duke.repos.IRepository;
import weijie.duke.responses.TaskResponse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class ListCommandTest {
    private IRepository<Task> repo;

    @BeforeEach
    void setup() {
        repo = new MockTaskRepo();
    }

    @Test
    void execute_listCommandWithNoTasksSaved_returnsEmptyList() {
        String[] args = "list".split(" ");

        ListCommand command = new ListCommand(repo);
        TaskResponse response = command.execute(args);

        assertIterableEquals(new ArrayList<>(), response.getTasks());
    }

    @Test
    void execute_listCommandWithFewTasksSaved_returnsCorrectListOfTasks() throws DukeIoException {
        final String[] args = "list".split(" ");

        Event event = new Event("2103 project due",
                LocalDateTime.of(2019, 8, 29, 23, 59));
        Deadline deadline = new Deadline("ok",
                LocalDateTime.of(2020, 12, 3, 2, 4));
        Todo todo = new Todo("TODOOOO");

        List<Task> expectedList = new ArrayList<>();
        expectedList.add(event);
        expectedList.add(deadline);
        expectedList.add(todo);

        repo.create(event);
        repo.create(deadline);
        repo.create(todo);

        ListCommand command = new ListCommand(repo);
        TaskResponse response = command.execute(args);

        assertIterableEquals(expectedList, response.getTasks());
    }
}
