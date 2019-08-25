package jermi.command;

import jermi.component.Storage;
import jermi.component.TaskList;
import jermi.component.Ui;
import jermi.exception.JermiException;

import java.util.List;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        super();
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JermiException {
        List<String> tasks = taskList.find(this.keyword);

        for (int index = 1; index <= tasks.size(); index++) {
            tasks.set(index - 1, index + "." + tasks.get(index - 1));
        }
        tasks.add(0, "Here are the matching tasks in your list:");
        ui.echo(tasks.toArray(new String[0]));
    }

    @Override
    public boolean shouldExit() {
        return false;
    }
}
