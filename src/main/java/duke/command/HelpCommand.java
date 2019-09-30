package duke.command;

import java.util.List;

public class HelpCommand implements Command {
    @Override
    public List<String> run(String[] words) {
        return List.of("Here are the commands you can use:",
                "",
                "Task adding:",
                "deadline <description> /by <time>",
                "event <description> /at <time>",
                "todo <description>",
                "",
                "Task editing: (index is obtained from the list command)",
                "list",
                "done <index>",
                "delete <index>",
                "",
                "Task searching:",
                "find <query>",
                "view <date>",
                "",
                "Miscellaneous:",
                "help",
                "bye");
    }
}
