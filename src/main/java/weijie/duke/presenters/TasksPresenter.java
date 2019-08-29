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

    /**
     * <p>
     *     Starts the main flow of the app. Presenter will start to listen for user input from the Ui, and will
     *     create appropriate Commands and invoke them, returning any TaskResponses to the Ui to be displayed.
     * </p>
     */
    public void run() {
        ui.startDisplay();

        while (true) {
            String input = ui.readCommand();

            if (input.equals("bye")) {
                ui.printExit();
                break;
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

