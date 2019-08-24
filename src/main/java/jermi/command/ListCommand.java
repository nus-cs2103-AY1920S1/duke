package jermi.command;
import jermi.component.Storage;
import jermi.component.TaskList;
import jermi.component.Ui;
import jermi.exception.JermiException;
import jermi.task.Task;

import java.util.List;
import java.util.stream.Collectors;

public class ListCommand extends Command {

    public ListCommand() {
        super();
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JermiException {
        List<String> tasks = taskList
                .getList()
                .stream()
                .map(Task::toString)
                .collect(Collectors.toList());

        for (int ordering = 1; ordering <= tasks.size(); ordering++) {
            tasks.set(ordering - 1, ordering + "." + tasks.get(ordering - 1));
        }
        tasks.add(0, "Here are the tasks in your list:");
        ui.echo(tasks.toArray(new String[0]));
    }

    @Override
    public boolean shouldExit() {
        return false;
    }
}
