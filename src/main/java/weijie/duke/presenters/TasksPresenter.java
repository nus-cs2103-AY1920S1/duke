package weijie.duke.presenters;

import weijie.duke.commands.ITaskCommand;
import weijie.duke.commands.TaskCommandFactory;
import weijie.duke.exceptions.DukeException;
import weijie.duke.responses.TaskResponse;
import weijie.duke.views.Ui;


public class TasksPresenter {
    private Ui ui;
    private TaskCommandFactory factory;

    public TasksPresenter(Ui ui, TaskCommandFactory factory) {
        this.ui = ui;
        this.factory = factory;
    }

    public void run() {
        ui.startDisplay();
        boolean isExit = false;

        while (!isExit) {
            String input = ui.readCommand();

            if (input.equals("bye")) {
                ui.printExit();
                isExit = true;
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
    }
}

