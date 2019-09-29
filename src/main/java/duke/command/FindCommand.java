package duke.command;

import duke.exception.DukeException;
import duke.exception.ExceptionType;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FindCommand implements Command {
    private Scanner s;

    public FindCommand(String fullCommand) {
        super();
        this.s = new Scanner(fullCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            // first, try to get keyword
            s.next(); // command (find), to be ignored
            String keyword = s.nextLine();

            // if ok, find and return matching tasks
            TaskList matchingTasks = tasks.findTasks(keyword);
            if (matchingTasks.isEmpty()) {
                throw new DukeException(ExceptionType.NO_MATCHING_TASKS);
            } else {
                ui.showTasks(matchingTasks);
            }
        } catch (InputMismatchException e) {
            // user input after "find" is empty
            throw new DukeException(ExceptionType.KEYWORD_BLANK);
        }


    }
}
