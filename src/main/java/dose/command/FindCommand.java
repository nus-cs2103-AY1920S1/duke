package dose.command;

import dose.task.TaskList;
import dose.util.Storage;
import dose.util.Ui;
import dose.util.UiMessage;
import dose.util.exception.DoseException;
import dose.util.exception.ExceptionType;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FindCommand implements Command {
    private Scanner s;

    /**
     * Constructs a new FindCommand, given the full command issued by the user.
     * @param fullCommand Full command issued by the user.
     */
    public FindCommand(String fullCommand) {
        super();
        this.s = new Scanner(fullCommand);
    }

    /**
     * Finds a list of tasks matching the keyword issued by the user.
     * @param tasks List of tasks.
     * @param ui UI to display to the user.
     * @param storage Object that handles storage of task list to disk.
     * @throws DoseException Application-specific exception thrown during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DoseException {
        try {
            // first, try to get keyword
            s.next(); // command (find), to be ignored
            String keyword = s.nextLine();

            // if ok, find and return matching tasks
            TaskList matchingTasks = tasks.findTasks(keyword);
            if (matchingTasks.isEmpty()) {
                throw new DoseException(ExceptionType.NO_MATCHING_TASKS);
            } else {
                ui.showMessage(UiMessage.MATCHING_TASKS);
                ui.showTasks(matchingTasks);
            }
        } catch (InputMismatchException e) {
            // user input after "find" is empty
            throw new DoseException(ExceptionType.KEYWORD_BLANK);
        }
    }
}
