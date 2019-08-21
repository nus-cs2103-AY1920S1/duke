package weijie.duke.presenters;

import weijie.duke.models.Task;
import weijie.duke.repos.IRepository;
import weijie.duke.views.ConsoleView;

public class ConsolePresenter implements InputListener {

    private ConsoleView view;
    private IRepository repo;

    public ConsolePresenter(ConsoleView view, IRepository<Task> repo) {
        view.registerListener(this);
        this.view = view;
        this.repo = repo;
    }

    @Override
    public void onInputReceived(String input) {
        if (input.equals("bye")) {
            view.exit();
        } else {
            view.print(input);
        }
    }
}
