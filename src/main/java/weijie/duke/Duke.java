package weijie.duke;

import weijie.duke.presenters.TasksPresenter;
import weijie.duke.repos.TaskRepo;
import weijie.duke.views.ConsoleView;

public class Duke {

    public static void main(String[] args) {
        ConsoleView view = new ConsoleView();
        TasksPresenter presenter = new TasksPresenter(view, new TaskRepo());
        presenter.start();
    }
}
