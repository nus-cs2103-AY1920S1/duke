package duke;

import duke.command.ListCommand;
import duke.repos.Repository;
import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Task;
import duke.task.ToDos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class ListCommandTest {

    private Repository<Task> mockRepo;
    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy HH:mm");

    @BeforeEach
    void setup() {
        mockRepo = new MockTaskRepo();
    }

    @Test
    void execute_listCommandWithoutTask_returnEmptyList() {
        String input = "";
        ListCommand command = new ListCommand("", input.split("", 0), formatter);
        command.execute(mockRepo);

        assertIterableEquals(new ArrayList<Task>(), mockRepo.getAll());
    }

    @Test
    void execute_listCommandWithTask() throws IOException {
        Task todo = new ToDos("hey", formatter);
        Task deadline = new Deadlines("hey", formatter, new Date());
        Task event = new Events("hey", formatter, new Date());

        List<Task> expectedOutput = new ArrayList<>();
        expectedOutput.add(todo);
        expectedOutput.add(deadline);
        expectedOutput.add(event);

        mockRepo.create(todo);
        mockRepo.create(deadline);
        mockRepo.create(event);

        assertIterableEquals(expectedOutput, mockRepo.getAll());

    }
}
