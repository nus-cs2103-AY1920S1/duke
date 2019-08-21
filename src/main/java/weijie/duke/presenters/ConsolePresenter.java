package weijie.duke.presenters;

import weijie.duke.views.ConsoleView;

public class ConsolePresenter implements InputListener {

    private ConsoleView view;

    public ConsolePresenter(ConsoleView view) {
        view.registerListener(this);
        this.view = view;
    }

    @Override
    public void onInputReceived(String input) {

    }
}
