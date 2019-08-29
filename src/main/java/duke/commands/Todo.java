package duke.commands;

import duke.*;

import java.util.Map;

public class Todo extends Command {
    public Todo(String[] args) {
        super(args);
    }

    @Override
    public String getName() {
        return "todo";
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Map<String, String[]> switchArgs = parser.parse(args);

        String[] comArgs = switchArgs.get(getName());
        if(comArgs.length == 0) throw new DukeException("The description of a todo cannot be empty.");

        duke.tasks.Todo t = new duke.tasks.Todo(Parser.concatenate(comArgs));
        tasks.add(t);
        ui.say("added: " + t);
    }
}
