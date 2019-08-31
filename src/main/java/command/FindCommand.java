package command;

import main.Storage;
import main.TaskList;
import main.Ui;
import task.Task;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents the user command to find tasks with the keyword.
 */
public class FindCommand extends Command {

    private String[] temp;
    private String keywords;
    private ArrayList<Task> foundList = new ArrayList<Task>();

    /**
     * Creates the FindCommand object.
     *
     * @param temp Parsed array of string based on user input.
     */
    public FindCommand(String[] temp) {
        this.temp = temp;
        String[] keywordArray = Arrays.copyOfRange(temp, 1, temp.length);
        keywords = String.join(" ", keywordArray);
    }

    /**
     * Prints the list of Tasks with the defined keyword.
     *
     */
    public void execute(TaskList task, Ui ui, Storage storage) {
        if (keywords.equals("")) {
            ui.printMessage("Please input a valid keyword");
        } else {
            for (Task curr : task.getList()) {
                if (curr.getDescription().contains(keywords)) {
                    foundList.add(curr);
                }
            }
            if (!foundList.isEmpty()) {
                ui.printFound(foundList);
            } else {
                ui.printMessage("No matching task found");
            }
        }
    }
}
