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
    private String filePath;
    private TasksPresenter presenter;

    private Duke(String filePath) {
        this.filePath = filePath;
    }

    private void initDependencies() {
        ConsoleView view = new ConsoleView();

        try {
            TaskFileStorage storage = new TaskFileStorage(filePath);
            IRepository<Task> repo = new TaskRepo(storage);

            TaskCommandFactory factory = new TaskCommandFactory();
            factory.registerDependency(repo);
            factory.registerDependency(view);

            presenter = new TasksPresenter(view, factory);

        } catch (DukeIOException e) {
            view.printError(e);
            view.exit();
        }

    }

    private void run() {
        initDependencies();
        presenter.start();
    }

    public static void main(String[] args) {
        Duke duke = new Duke("data/duke.txt");
        duke.run();
    }
}
