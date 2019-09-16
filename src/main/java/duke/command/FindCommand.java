package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FindCommand extends Command {
    private static final Pattern PAT = Pattern.compile(" ");
    String keyWords;

    /**
     * Initialise a FindCommand.
     *
     * @param commandContent User input string less command word.
     * @throws DukeException If user input argument is empty.
     */
    public FindCommand(String commandContent) throws DukeException {
        super();

        if (commandContent.isEmpty()) {
            throw new DukeException("OOPS!!! The object to find cannot be empty.");
        }

        keyWords = commandContent;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        List<Task> foundTasks = PAT.splitAsStream(keyWords)
                .map(x -> tasks.findByKeyword(x))
                .flatMap(x -> x.stream())
                .distinct()
                .collect(Collectors.toList());
        ui.showFoundTasks(foundTasks);
    }
}
