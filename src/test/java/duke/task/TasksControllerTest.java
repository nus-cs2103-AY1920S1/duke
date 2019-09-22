package duke.task;

import duke.command.entities.TaskSorts;
import duke.task.tasks.Deadline;
import duke.task.tasks.Event;
import duke.task.tasks.TasksControllerFeedback;
import duke.task.tasks.ToDo;
import error.task.TaskRepoException;
import error.ui.UiException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import storage.InMemStorage;
import ui.StubOutput;
import ui.Ui;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class TasksControllerTest {
    private StubOutput output;
    private Ui ui;
    private ITaskRepo repo;
    private TasksController controller;

    private void generateMocks() {
        this.output = new StubOutput();
        this.ui = new Ui(null, output, null);
        this.repo = new DefaultTaskRepo(new InMemStorage());
        this.controller = new TasksController(this.repo);
    }

    private List<Task> generateMockTasks() {
        List<Task> mockTasks = new ArrayList<>();
        ToDo mockTaskA = new ToDo("helloc", false, false);
        Event mockTaskB = new Event("hellob", LocalDateTime.now(), false, false);
        Deadline mockTaskC = new Deadline("helloa", LocalDateTime.now(), false, false);
        mockTasks.add(mockTaskA);
        mockTasks.add(mockTaskB);
        mockTasks.add(mockTaskC);

        return mockTasks;
    }


    @Test
    void registerUi() throws UiException {
        this.generateMocks();

        controller.registerUi(ui.getUiOutputAccessor());
        controller.deleteAllTasks();
        String message = output.getReceivedOutputs().get(0);
        String expectedMessage = new TasksControllerFeedback().displayAllTasksDeleted();

        Assertions.assertEquals(message, expectedMessage);
    }

    @Test
    void listTasks() throws UiException, TaskRepoException {
        this.generateMocks();

        List<Task> tasks = this.generateMockTasks();
        this.repo.setNewTasks(tasks);

        controller.registerUi(ui.getUiOutputAccessor());
        controller.listTasks();

        String message = output.getReceivedOutputs().get(0);
        String expectedMessage = new TasksControllerFeedback().displayAllTasks(tasks);

        Assertions.assertEquals(message, expectedMessage);
    }

    @Test
    void addTask() throws UiException, TaskRepoException {
        this.generateMocks();

        Task mockTaskA = new ToDo("hello", false, false);
        this.controller.addTask(mockTaskA);

        Assertions.assertTrue(repo.getCurrentTasks().contains(mockTaskA));
    }

    @Test
    void setTaskToDone() throws TaskRepoException, UiException {
        this.generateMocks();

        Task mockTaskA = new ToDo("hello", false, false);
        this.repo.addTask(mockTaskA);
        this.controller.setTaskToDone(0);

        Assertions.assertTrue(repo.getTaskFromListIndex(0).isTaskDone());
    }

    @Test
    void deleteTask() throws TaskRepoException, UiException {
        this.generateMocks();

        Task mockTaskA = new ToDo("hello", false, false);
        this.repo.addTask(mockTaskA);
        this.controller.deleteTask(0);

        Assertions.assertFalse(repo.getCurrentTasks().contains(mockTaskA));
    }

    @Test
    void findTasks() throws UiException, TaskRepoException {
        this.generateMocks();

        List<Task> tasks = this.generateMockTasks();
        this.repo.addTask((Task) tasks);

        controller.findTasks("a");
        String message = output.getReceivedOutputs().get(0);

        List<Task> expectedTasks = tasks.stream()
                .filter(task -> task.getTaskDetails().toLowerCase().contains("a"))
                .collect(Collectors.toList());
        String expectedMessage = new TasksControllerFeedback().displayMatchingTasks(expectedTasks);

        Assertions.assertEquals(message, expectedMessage);
    }

    @Test
    void sortTasks() throws TaskRepoException, UiException {
        this.generateMocks();

        List<Task> tasks = this.generateMockTasks();
        this.repo.setNewTasks(tasks);

        this.controller.sortTasks(TaskSorts.NAME);
        tasks.sort(TaskSorts.NAME.comparator);

        List<Task> sortedTasks = this.repo.getCurrentTasks();
        for (int i = 0; i < sortedTasks.size(); i++) {
            Assertions.assertEquals(tasks.get(i), sortedTasks.get(i));
        }
    }

    @Test
    void deleteAllTasks() throws TaskRepoException, UiException {
        this.generateMocks();

        List<Task> tasks = this.generateMockTasks();
        this.repo.setNewTasks(tasks);

        this.controller.deleteAllTasks();

        Assertions.assertEquals(this.repo.getCurrentTasks().size(), 0);
    }
}