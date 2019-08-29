package weijie.duke;

import weijie.duke.commands.*;
import weijie.duke.db.TaskFileStorage;
import weijie.duke.exceptions.DukeException;
import weijie.duke.exceptions.DukeIOException;
import weijie.duke.models.Task;
import weijie.duke.presenters.TasksPresenter;
import weijie.duke.repos.IRepository;
import weijie.duke.repos.TaskRepo;
import weijie.duke.views.ConsoleView;

import java.util.HashMap;

public class Duke {

    public static void main(String[] args) {

        ConsoleView view = new ConsoleView();

        try {
            TaskFileStorage storage = new TaskFileStorage("data/duke.txt");
            IRepository<Task> repo = new TaskRepo(storage);

            HashMap<String, ITaskCommand> commandMap = new HashMap<>();
            commandMap.put("list", new ListCommand(repo));
            commandMap.put("todo", new AddTodoCommand(repo));
            commandMap.put("deadline", new AddDeadlineCommand(repo));
            commandMap.put("event", new AddEventCommand(repo));
            commandMap.put("done", new DoneCommand(repo));
            commandMap.put("delete", new DeleteCommand(repo));
            TaskCommandFactory factory = new TaskCommandFactory(commandMap);

            TasksPresenter presenter = new TasksPresenter(view, factory);
            presenter.start();

        } catch (DukeIOException e) {
            view.printError(e);
            view.exit();
        }


    }
}
