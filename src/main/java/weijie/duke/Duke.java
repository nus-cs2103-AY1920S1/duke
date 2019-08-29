package weijie.duke;

import weijie.duke.commands.CommandList;
import weijie.duke.commands.TaskCommandFactory;
import weijie.duke.db.Storage;
import weijie.duke.exceptions.DukeIoException;
import weijie.duke.models.Task;
import weijie.duke.presenters.TasksPresenter;
import weijie.duke.repos.IRepository;
import weijie.duke.repos.TaskRepo;
import weijie.duke.views.Ui;


public class Duke {
    private String filePath;
    private TasksPresenter presenter;

    private Duke(String filePath) {
        this.filePath = filePath;
    }

    private void initDependencies() {
        Ui ui = new Ui();

        try {
            Storage storage = new Storage(filePath);
            IRepository<Task> repo = new TaskRepo(storage);

            TaskCommandFactory factory = new TaskCommandFactory(CommandList.getCommandMap());
            factory.registerDependency(repo);
            factory.registerDependency(ui);

            presenter = new TasksPresenter(ui, factory);

        } catch (DukeIoException e) {
            ui.printError(e);
            ui.printExit();
        }

    }

    private void run() {
        initDependencies();
        presenter.run();
    }

    public static void main(String[] args) {
        Duke duke = new Duke("data/duke.txt");
        duke.run();
    }
}
