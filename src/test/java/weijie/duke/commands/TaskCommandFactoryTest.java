package weijie.duke.commands;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import weijie.duke.db.FileSystemStorage;
import weijie.duke.db.ITaskStorage;
import weijie.duke.exceptions.DukeException;
import weijie.duke.exceptions.DukeIllegalArgumentException;
import weijie.duke.models.Task;
import weijie.duke.repos.IRepository;
import weijie.duke.repos.TaskRepo;
import weijie.duke.responses.TaskResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Testing dependency injection of the TaskCommandFactory.
 */
class TaskCommandFactoryTest {
    private TaskCommandFactory taskCommandFactory;

    @BeforeEach
    void setup() {
        Map<String, Class<? extends ITaskCommand>> commandMap = new HashMap<>();
        commandMap.put("no dependency", NoDependencyCommand.class);
        commandMap.put("many dependency", ManyDependencyCommand.class);

        taskCommandFactory = new TaskCommandFactory(commandMap);
    }

    @Test
    void tryMakeCommand_makeNoDependencyCommand_returnsAppropriateCommand() throws DukeException {
        ITaskCommand actualCommand = taskCommandFactory.tryMakeCommand("no dependency");
        assertThat(actualCommand, samePropertyValuesAs(new NoDependencyCommand()));
    }

    @Test
    void tryMakeCommand_makeManyDependencyCommand_returnsAppropriateCommand() throws DukeException {
        // No need to use mocks/stubs, in this case behaviour of the classes is not important
        FileSystemStorage storage = new FileSystemStorage("abc/123");
        TaskRepo taskRepo = new TaskRepo(storage);
        CommandHistory commandHistory = new CommandHistory();
        taskCommandFactory.registerDependency(taskRepo);
        taskCommandFactory.registerDependency(storage);
        taskCommandFactory.registerDependency(commandHistory);

        ITaskCommand actualCommand = taskCommandFactory.tryMakeCommand("many dependency");
        assertThat(actualCommand, samePropertyValuesAs(new ManyDependencyCommand(taskRepo, commandHistory, storage)));
    }

    @Test
    void registerDependency_registerPrimitiveDependencies_throwsIllegalArgumentException() {
        assertThrows(DukeIllegalArgumentException.class, () ->
                taskCommandFactory.registerDependency(2));

        assertThrows(DukeIllegalArgumentException.class, () ->
                taskCommandFactory.registerDependency(true));

        assertThrows(DukeIllegalArgumentException.class, () ->
                taskCommandFactory.registerDependency('3'));

        assertThrows(DukeIllegalArgumentException.class, () ->
                taskCommandFactory.registerDependency(0.1231));
    }


    static class NoDependencyCommand implements ITaskCommand {

        @Override
        public TaskResponse execute(String... args) {
            return null;
        }

        @Override
        public Optional<CommandState> getCommandState() {
            return Optional.empty();
        }
    }

    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    static class ManyDependencyCommand implements ITaskCommand {

        private final IRepository<Task> repo;
        private final CommandHistory history;
        private final ITaskStorage storage;

        ManyDependencyCommand(IRepository<Task> repo, CommandHistory history, ITaskStorage storage) {

            this.repo = repo;
            this.history = history;
            this.storage = storage;
        }

        @Override
        public TaskResponse execute(String... args) {
            return null;
        }

        @Override
        public Optional<CommandState> getCommandState() {
            return Optional.empty();
        }
    }

}

