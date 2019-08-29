package weijie.duke.presenters;

import weijie.duke.commands.ITaskCommand;
import weijie.duke.commands.TaskCommandFactory;
import weijie.duke.exceptions.DukeException;
import weijie.duke.responses.TaskResponse;
import weijie.duke.views.Ui;


public class TasksPresenter implements ConsoleInputListener {

    private Ui ui;
    private TaskCommandFactory factory;

    public TasksPresenter(Ui ui, TaskCommandFactory factory) {
        ui.registerListener(this);
        this.ui = ui;
        this.factory = factory;
    }

    @Override
    public void onInputReceived(String input) {
        if (input.equals("bye")) {
            ui.exit();
            return;
        }

        String[] args = input.split(" ");

        try {
            ITaskCommand command = factory.tryMakeCommand(args[0]);
            TaskResponse response = command.execute(args);
            ui.print(response);

        } catch (DukeException e) {
            ui.printError(e);
        }
    }

    public void start() {
        ui.startDisplay();
    }
}

