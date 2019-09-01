package duke;

import java.util.StringJoiner;

public class ListCommand extends Command {
    ListCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public String execute(TaskList tasks) {
        StringJoiner sj = new StringJoiner("\n");
        for (int i = 0; i < tasks.getTasks().size(); i++) {
            sj.add((i + 1) + ". " + tasks.getTasks().get(i));
        }
        return sj.toString();
    }
}
