package duke.text;

import duke.command.Command;
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
    String keyWord;

    public FindCommand(String keyWord) {
        super();
        this.keyWord = keyWord;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        List<Task> foundTasks = PAT.splitAsStream(keyWord)
                .map(x -> tasks.find(x))
                .flatMap(x -> x.stream())
                .distinct()
                .collect(Collectors.toList());
        ui.showFoundTasks(foundTasks);
    }
}
