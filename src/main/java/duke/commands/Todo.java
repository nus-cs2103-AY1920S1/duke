package duke.commands;

import duke.Command;
import duke.Duke;

public class Todo extends Command {
    public Todo(Duke duke) {
        super(duke);
        name = "todo";
    }
    public void execute(String[] args) {
        String description = "";
        for(int i = 1; i < args.length; i++) {
            description = description + args[i];
            if(i != args.length-1) description = description + " ";
        }
        duke.addTask(new duke.tasks.Todo(description));
    }
}
