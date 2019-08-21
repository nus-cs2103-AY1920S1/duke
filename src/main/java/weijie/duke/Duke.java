package weijie.duke;

import weijie.duke.presenters.ConsolePresenter;
import weijie.duke.repos.TaskRepo;
import weijie.duke.views.ConsoleView;

public class Duke {

    public static void main(String[] args) {
        ConsoleView view = new ConsoleView();
        ConsolePresenter presenter = new ConsolePresenter(view, new TaskRepo());
        view.init();
    }
}
