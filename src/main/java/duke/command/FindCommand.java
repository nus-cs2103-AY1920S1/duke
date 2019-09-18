package duke.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import duke.task.TaskList;

public class FindCommand implements Command {
    private TaskList tasks;

    public FindCommand(TaskList tasks) {
        this.tasks = tasks;
    }

    @Override
    public List<String> run(String[] words) {
        String query = String.join(" ", Arrays.copyOfRange(words, 1, words.length));
        List<String> messages = new ArrayList<>();
        messages.add("Here are the matching tasks in your list:");
        int count = 0;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).toString().contains(query)) {
                count++;
                messages.add(count + "." + tasks.get(i));
            }
        }
        return messages;
    }
}
