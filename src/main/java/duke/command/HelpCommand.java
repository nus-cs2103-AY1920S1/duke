package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class HelpCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder lines = new StringBuilder();
        lines.append("These are my supported commands:\n");
        lines.append("todo - Adds a todo task. \n\tUsage: todo <name of task>.\n");
        lines.append("deadline - Adds a task with a deadline. \n\tUsage: deadline <name of deadline> /by DD/MM/YY HH:MM\n");
        lines.append("event - Adds task with a start time. \n\tUsage: event <name of event> /at DD/MM/YY HH:MM\n");
        lines.append("done - Marks a task as completed. \n\tUsage: done <id> <id>...\n");
        lines.append("delete - Deletes a task. \n\tUsage: delete <id> <id>...\n");
        lines.append("find - Filter through tasks. \n\tUsage: find <keyword>\n");
        lines.append("list - Lists all tasks. \n\tUsage: list\n");
        lines.append("help - Show the help instructions. \n\tUsage: help\n");
        lines.append("bye - Closes PokéTask. \n\tUsage: bye\n");
        ui.append(lines.toString());
    }
}
