package weijie.duke;

import javafx.application.Application;
import javafx.stage.Stage;
import weijie.duke.commands.CommandList;
import weijie.duke.commands.TaskCommandFactory;
import weijie.duke.db.Storage;
import weijie.duke.exceptions.DukeIoException;
import weijie.duke.models.Task;
import weijie.duke.presenters.TasksPresenter;
import weijie.duke.repos.IRepository;
import weijie.duke.repos.TaskRepo;
import weijie.duke.views.Ui;


public class Duke extends Application {
    private String filePath;
    private TasksPresenter presenter;

    private Duke() {
        this.filePath = "data/duke.txt";
    }

    @Override
    public void start(Stage primaryStage) {
        initDependencies();
        presenter.run();
    }

    private void initDependencies() {
        Ui ui = new Ui();

        try {
            Storage storage = new Storage(filePath);
            IRepository<Task> repo = new TaskRepo(storage);

            TaskCommandFactory factory = new TaskCommandFactory(CommandList.getCommandMap());
            factory.registerDependency(repo);

            presenter = new TasksPresenter(ui, factory);

        } catch (DukeIoException e) {
            ui.printError(e);
            ui.printExit();
        }
    }
}
