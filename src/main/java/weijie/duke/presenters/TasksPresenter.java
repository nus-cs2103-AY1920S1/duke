package weijie.duke.presenters;

import weijie.duke.commands.ITaskCommand;
import weijie.duke.commands.TaskCommandFactory;
import weijie.duke.responses.TaskResponse;
import weijie.duke.views.ConsoleView;


public class TasksPresenter implements ConsoleInputListener {

    private ConsoleView view;
    private TaskCommandFactory factory;

    public TasksPresenter(ConsoleView view, TaskCommandFactory factory) {
        view.registerListener(this);
        this.view = view;
        this.factory = factory;
    }

    @Override
    public void onInputReceived(String input) {
        if (input.equals("bye")) {
            view.exit();
            return;
        }

        String[] args = input.split(" ");

        ITaskCommand command = factory.tryMakeCommand(args[0]);

        TaskResponse response = command.execute(args);
        view.print(response);
    }

    public void start() {
        view.startDisplay();
    }
}

