package duke.command;

import duke.storage.Storage;
import duke.task.*;
import duke.ui.Ui;

import java.text.ParseException;

/**
 * This class controls the logic of adding the tasks.
 */
public class AddTaskCommand extends Command {

    private String instruction;
    private String description;

    public AddTaskCommand(String instruction, String description) {
        this.instruction = instruction;
        this.description = description;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {

        Task task;
        String[] words = description.split(" ");
        StringBuilder descriptions = new StringBuilder();

        switch(instruction) {
            case "todo":
                task = new ToDo(description);
                tasks.addTask(task);
                return ui.addTask(task, tasks.getNumber());

            case "event":

                StringBuilder at = new StringBuilder();
                for (int i = 0; i < words.length; i++) {
                    String word = words[i];
                    if (!word.equals("/at")) {
                        descriptions.append(words[i]);
                        descriptions.append(" ");
                    } else {
                        for (int j = i + 1; j < words.length; j++) {
                            at.append(words[j]);
                            at.append(" ");
                        }
                        break;
                    }
                }
                task = new Event(descriptions.toString().trim(), at.toString().trim());
                tasks.addTask(task);
                return ui.addTask(task, tasks.getNumber());

            case "deadline":
                StringBuilder by = new StringBuilder();
                for (int i = 0; i < words.length; i++) {
                    String word = words[i];
                    if (!word.equals("/by")) {
                        descriptions.append(words[i]);
                        descriptions.append(" ");
                    } else {
                        for (int j = i + 1; j < words.length; j++) {
                            by.append(words[j]);
                            by.append(" ");
                        }
                        break;
                    }
                }
                task = new Deadline(descriptions.toString().trim(), by.toString().trim());
                tasks.addTask(task);
                return ui.addTask(task, tasks.getNumber());

            default:
                return "This shouldn't appear.";
        }

    }

}
