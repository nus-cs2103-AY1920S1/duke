package seedu.duke;

import javafx.application.Platform;
import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.commands.ExitCommand;
import seedu.duke.tasks.TaskManager;

import java.util.function.Function;

public class Duke {
    private TaskManager taskManager;
    private Function<String, Void> feedbackHandler;

    /**
     * Constructor for Duke chat bot.
     *
     * @param filePath Path to the file that stores the tasks data.
     * @throws Exception An error that occurred during initial launch of the chat bot.
     */
    public Duke(String filePath) throws Exception {
        this.taskManager = new TaskManager(filePath);
        feedbackHandler = (str) -> null;
    }

    void setFeedbackHandler(Function<String, Void> feedbackHandler) {
        this.feedbackHandler = feedbackHandler;
    }

    /**
     * Shows a header that is printed at the start of the chat bot.
     */
    String getInitialMessage() {
        return "Hello! I'm Duke\nWhat can I do for you?";
    }

    /**
     * Returns response from duke chat bot. Initial data will be loaded from the
     * <code>filePath</code> that is specified upon initialization of <code>Duke</code> class.
     */
    void handleUserInput(String inputLine) {
        try {
            Command command = Command.parseCommand(inputLine);
            CommandResult commandResult = command.execute(taskManager);
            feedbackHandler.apply(commandResult.getFeedback());
            if (ExitCommand.isExit(command)) {
                Platform.exit();
            }
        } catch (Exception e) { // Show an error message to the user that is caught during the program execution.
            feedbackHandler.apply(e.getMessage());
        }
    }
}
