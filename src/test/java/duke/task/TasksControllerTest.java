package duke.task;

import duke.command.sort.TaskSorts;
import duke.task.tasks.Deadline;
import duke.task.tasks.Event;
import duke.task.tasks.ToDo;
import error.task.TaskCreationException;
import error.task.TaskRepoException;
import error.ui.UiException;
import error.ui.UiInitializationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import storage.InMemStorage;
import ui.StubInput;
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

    private void generateMocks() throws UiInitializationException {
        this.output = new StubOutput();
        this.ui = new Ui(new StubInput(null), output, null);
        this.repo = new DefaultTaskRepo(new InMemStorage());
        this.controller = new TasksController(this.repo);
        this.ui.initializeUi();

        this.controller.registerUi(this.ui.getUiOutputAccessor());
    }

    private List<Task> generateMockTasks() throws TaskCreationException {
        List<Task> mockTasks = new ArrayList<>();
        ToDo mockTaskA = new ToDo("helloc");
        Event mockTaskB = new Event("hellob", LocalDateTime.now());
        Deadline mockTaskC = new Deadline("helloa", LocalDateTime.now());
        mockTasks.add(mockTaskA);
        mockTasks.add(mockTaskB);
        mockTasks.add(mockTaskC);

        return mockTasks;
    }


    @Test
    void registerUi() throws UiException, UiInitializationException {
        this.generateMocks();

        controller.deleteAllTasks();
        String message = output.getReceivedOutputs().get(0);
        String expectedMessage = new TasksControllerFeedback().displayAllTasksDeleted();

        Assertions.assertEquals(message, expectedMessage);
    }

    @Test
    void listTasks() throws UiException, TaskRepoException, TaskCreationException, UiInitializationException {
        this.generateMocks();

        List<Task> tasks = this.generateMockTasks();
        this.repo.setNewTasks(tasks);

        controller.listTasks();

        String message = output.getReceivedOutputs().get(0);
        String expectedMessage = new TasksControllerFeedback().displayAllTasks(tasks);

        Assertions.assertEquals(message, expectedMessage);
    }

    @Test
    void addTask() throws UiException, TaskRepoException, TaskCreationException, UiInitializationException {
        this.generateMocks();

        Task mockTaskA = new ToDo("hello");
        this.controller.addTask(mockTaskA);

        Assertions.assertTrue(repo.getCurrentTasks().contains(mockTaskA));
    }

    @Test
    void setTaskToDone() throws TaskRepoException, UiException, TaskCreationException, UiInitializationException {
        this.generateMocks();

        Task mockTaskA = new ToDo("hello");
        this.repo.addTask(mockTaskA);
        this.controller.setTaskToDone(0);

        Assertions.assertTrue(repo.getTaskFromListIndex(0).isTaskDone());
    }

    @Test
    void deleteTask() throws TaskRepoException, UiException, TaskCreationException, UiInitializationException {
        this.generateMocks();

        Task mockTaskA = new ToDo("hello");
        this.repo.addTask(mockTaskA);
        this.controller.deleteTask(0);

        Assertions.assertFalse(repo.getCurrentTasks().contains(mockTaskA));
    }

    @Test
    void findTasks() throws UiException, TaskRepoException, TaskCreationException, UiInitializationException {
        this.generateMocks();

        List<Task> tasks = this.generateMockTasks();
        this.repo.setNewTasks(tasks);

        controller.findTasks("a");
        String message = output.getReceivedOutputs().get(0);

        List<Task> expectedTasks = tasks.stream()
                .filter(task -> task.getTaskDetails().toLowerCase().contains("a"))
                .collect(Collectors.toList());
        String expectedMessage = new TasksControllerFeedback().displayMatchingTasks(expectedTasks);

        Assertions.assertEquals(message, expectedMessage);
    }

    @Test
    void sortTasks() throws TaskRepoException, UiException, TaskCreationException, UiInitializationException {
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
    void deleteAllTasks() throws TaskRepoException, UiException, TaskCreationException, UiInitializationException {
        this.generateMocks();

        List<Task> tasks = this.generateMockTasks();
        this.repo.setNewTasks(tasks);

        this.controller.deleteAllTasks();

        Assertions.assertEquals(this.repo.getCurrentTasks().size(), 0);
    }

    @Test
    void deleteTaskByUuid() throws TaskCreationException, TaskRepoException, UiInitializationException, UiException {
        this.generateMocks();

        List<Task> tasks = this.generateMockTasks();
        this.repo.setNewTasks(tasks);

        Task taskToBeDeleted = tasks.get(1);
        this.controller.deleteTaskByUuid(taskToBeDeleted.getUuid());

        Assertions.assertFalse(this.repo.getCurrentTasks().stream()
                .anyMatch(task -> task.getUuid().equals(taskToBeDeleted.getUuid())));
    }

    @Test
    void setNewTasks() throws UiInitializationException, TaskCreationException, UiException, TaskRepoException {
        this.generateMocks();

        List<Task> tasks = this.generateMockTasks();
        this.controller.setNewTasks(tasks);

        List<Task> storedTasks = this.repo.getCurrentTasks();

        for (int i = 0; i < tasks.size(); i++) {
            Assertions.assertEquals(tasks.get(i), storedTasks.get(i));
        }
    }

    @Test
    void setTask() throws TaskCreationException, TaskRepoException, UiInitializationException, UiException {
        this.generateMocks();

        List<Task> tasks = this.generateMockTasks();
        this.repo.setNewTasks(tasks);

        Task mockTaskA = new ToDo("Lubababadubdub");
        this.controller.setTask(0, mockTaskA);
        Assertions.assertEquals(this.repo.getCurrentTasks().size(), tasks.size());
        Assertions.assertEquals(this.repo.getTaskFromListIndex(0), mockTaskA);
    }
}