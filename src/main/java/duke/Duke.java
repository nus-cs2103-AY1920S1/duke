package duke;

import duke.command.Command;
import duke.command.bye.ByeCommandProducer;
import duke.command.creation.AddCommandFactory;
import duke.command.creation.MainCommandFactory;
import duke.command.creation.UndoCommandFactory;
import duke.command.delete.DeleteCommandProducer;
import duke.command.done.DoneCommandProducer;
import duke.command.find.FindCommandProducer;
import duke.command.help.HelpCommandProducer;
import duke.command.list.ListCommandProducer;
import duke.command.sort.SortCommandProducer;
import duke.task.DefaultTaskRepo;
import duke.task.TasksController;
import duke.task.ITaskRepo;
import error.command.CommandCreationException;
import error.command.CommandProducerRegisterException;
import error.ui.UiException;
import error.ui.UiInitializationException;
import storage.Storage;
import ui.Ui;
import ui.UiDriver;
import util.strings.ErrorMessageFormatter;

import java.util.Optional;

/**
 * Main driver class for Duke task manager program.
 */

public class Duke implements UiDriver {
    private Ui ui;
    private MainCommandFactory mainCommandFactory;
    private TasksController tasksController;
    private CommandExecutor commandExecutor;

    /**
     * Program entry point.
     *
     * @param args program arguments
     */
    public static void main(String[] args) {
        Duke duke = new Duke();

        boolean isGuiEnabled;

        if (args.length == 0) {
            isGuiEnabled = true;
        } else if (args.length == 1 && args[0].equals("-c")) {
            isGuiEnabled = false;
        } else {
            System.out.println("Invalid program arguments.");
            System.exit(1);
            return;
        }

        try {
            DukeOptions options = OptionsFactory.select(isGuiEnabled, true, duke);
            duke.configure(options);
        } catch (Exception e) {
            System.out.println("FATAL: Unable to configure application.");
            System.exit(1);
            return;
        }

        duke.run();
    }

    /**
     * Starts the program.
     */
    public void run() {
        System.out.println("Program starting...");

        try {
            ui.initializeUi();
        } catch (UiInitializationException e) {
            System.out.println("FATAL: Failed to initialize ui.");
            System.exit(1);
        }
    }

    /**
     * Configures the main driver with a set of customizable options.
     *
     * @param options the program's runtime options
     */
    public void configure(DukeOptions options) {
        // Initialize UI component
        this.ui = options.getUiController();

        // Initialize tasks and storage
        Storage storage = options.getStorage();
        ITaskRepo model = new DefaultTaskRepo(storage);
        this.tasksController = new TasksController(model);
        this.tasksController.registerUi(this.ui.getUiOutputAccessor());
        this.commandExecutor = new CommandExecutor();

        // Initialize command factory
        this.mainCommandFactory = intializeCommandFactory();
    }


    @Override
    public void receiveUserInput(String input) {
        try {
            // Get command and execute
            Optional<Command> command = this.mainCommandFactory.getCommandFromUserInput(input);
            if (command.isPresent()) {
                this.commandExecutor.executeCommand(command.get());
            } else {
                ui.displayOutput(ErrorMessageFormatter.formatErrorMessage("Please enter a valid command."));
            }

        } catch (CommandCreationException e) {
            this.handleCommandCreationExceptions(e);
        } catch (UiException e) {
            System.out.println("FATAL: Ui stopped working.");
            System.exit(1);
        }
    }

    private void handleCommandCreationExceptions(CommandCreationException error) {
        try {
            this.ui.displayOutput(ErrorMessageFormatter.formatErrorMessage(error.getMessage()));
        } catch (UiException e) {
            System.out.println("FATAL: Ui stopped working.");
            System.exit(1);
        }
    }

    private MainCommandFactory intializeCommandFactory() {
        MainCommandFactory mainFactory = new MainCommandFactory();

        // register CommandProducers
        try {

            mainFactory.registerCommandProducer(new ByeCommandProducer(this.ui));
            mainFactory.registerCommandProducer(new DeleteCommandProducer(this.tasksController));
            mainFactory.registerCommandProducer(new ListCommandProducer(this.tasksController));
            mainFactory.registerCommandProducer(new SortCommandProducer(this.tasksController));
            mainFactory.registerCommandProducer(new DoneCommandProducer(this.tasksController));
            mainFactory.registerCommandProducer(new FindCommandProducer(this.tasksController));
            mainFactory.registerCommandProducer(new HelpCommandProducer(this.ui));

        } catch (CommandProducerRegisterException e) {
            System.out.println("FATAL: Unable to register command producer.");
            System.exit(1);
        }

        // create factories
        AddCommandFactory addCommandFactory = new AddCommandFactory(this.tasksController);
        UndoCommandFactory undoCommandFactory = new UndoCommandFactory(this.commandExecutor, this.ui);

        // register factories
        mainFactory.registerCommandFactory(addCommandFactory);
        mainFactory.registerCommandFactory(undoCommandFactory);

        return mainFactory;
    }
}
