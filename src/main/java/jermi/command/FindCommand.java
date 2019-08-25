package jermi.command;

import jermi.component.Storage;
import jermi.component.TaskList;
import jermi.component.Ui;
import jermi.exception.JermiException;

import java.util.List;

public class FindCommand extends Command {
    /** Keyword used for finding */
    private String keyword;

    /**
     * Public constructor for class.
     *
     * @param keyword Keyword used for finding.
     */
    public FindCommand(String keyword) {
        super();
        this.keyword = keyword;
    }

    /**
     * Executes the command.
     *
     * @param taskList Task list.
     * @param ui UI.
     * @param storage Storage.
     * @throws JermiException JermiException.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JermiException {
        List<String> tasks = taskList.find(this.keyword);

        for (int index = 1; index <= tasks.size(); index++) {
            tasks.set(index - 1, index + "." + tasks.get(index - 1));
        }
        tasks.add(0, "Here are the matching tasks in your list:");
        ui.echo(tasks.toArray(new String[0]));
    }

    /**
     * Indicates if the program should exit.
     *
     * @return {@code false}.
     */
    @Override
    public boolean shouldExit() {
        return false;
    }
}
