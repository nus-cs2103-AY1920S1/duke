package weijie.duke;

import weijie.duke.commands.AddCommand;
import weijie.duke.commands.ITaskCommand;
import weijie.duke.commands.ListCommand;
import weijie.duke.commands.TaskCommandFactory;
import weijie.duke.models.Task;
import weijie.duke.presenters.TasksPresenter;
import weijie.duke.repos.IRepository;
import weijie.duke.repos.TaskRepo;
import weijie.duke.views.ConsoleView;

import java.util.HashMap;

public class Duke {

    public static void main(String[] args) {
        ConsoleView view = new ConsoleView();

        IRepository<Task> repo = new TaskRepo();

        HashMap<String, ITaskCommand> commandMap = new HashMap<>();
        commandMap.put("list", new ListCommand(repo));
        commandMap.put("add", new AddCommand(repo));
        TaskCommandFactory factory = new TaskCommandFactory(commandMap);

        TasksPresenter presenter = new TasksPresenter(view, factory);
        presenter.start();
    }
}
