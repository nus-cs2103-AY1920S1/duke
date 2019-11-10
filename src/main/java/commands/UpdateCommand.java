package commands;

import exception.DukeException;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import tasks.TimeframedTask;
import ui.Ui;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Class for the command that updates a task based on the input fields.
 */
public class UpdateCommand extends IndexingCommand {

    private String commandInput;

    /**
     * Constructs the update command.
     *
     * @param taskNum the index of the task to be updated
     * @param commandInput the string of items to change
     */
    public UpdateCommand(int taskNum, String commandInput) {
        super.taskNumber = taskNum;
        this.commandInput = commandInput;
    }

    /**
     * Indicates whether this is an exit command.
     *
     * @return false
     */
    public boolean isExitCommand() {
        return false;
    }

    /**
     * Executes the update command in the form of "update [taskNumber] [/field] [new field]"
     * Given task at the index position will be updated based on the given fields.
     * A completion message will be displayed after successful update.
     *
     * @param tasks tasklist that stores all the tasks
     * @param ui ui object that deals with user interaction
     * @param storage storage object that deals with writing into the text file
     * @return string representation of the command response
     * @throws DukeException exception is thrown when the index falls outside the number of tasks
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (isTaskNumOutOfRange(taskNumber, tasks.getNumOfTask())) {
            throw new DukeException("List only has " + tasks.getNumOfTask() + " items.");
        } else {
            Task curTask = tasks.getTaskByIndex(taskNumber - 1);
            String[] newDetails = parseCommand(); // parses command and gets new details
            String newDescription = newDetails[0];
            String newDateTime = newDetails[1];

            if (!newDescription.isEmpty()) {
                curTask.setDescription(newDescription.trim()); // updates description field of task
            }
            if (!newDateTime.isEmpty()) {
                TimeframedTask curTimeframedTask = (TimeframedTask)curTask;
                curTimeframedTask.setDateTime(newDateTime);
            }
            return ui.showResultOfCommand("     Noted. I've updated this task:\n       "
                                                  + curTask);

        }
    }

    private String[] parseCommand() throws DukeException {
        String newDescription = "";
        String newDateTime = "";
        Scanner commandDetailsParser = new Scanner(commandInput);
        String word;

        // loop to parse command
        try {
            word = commandDetailsParser.next();
        } catch (NoSuchElementException e) {
            throw new DukeException("Please indicate the field(s) you want to update.");
        }
        while (commandDetailsParser.hasNext()) {
            String nextWord = commandDetailsParser.next();

            if (word.equals("/description")) {
                try {
                    while (!nextWord.contains("/")) {
                        newDescription += nextWord + " ";
                        if (commandDetailsParser.hasNext()) {
                            nextWord = commandDetailsParser.next();
                        } else {
                            break;
                        }
                    }
                } catch (NullPointerException e) {
                    throw new DukeException("Description field cannot be empty");
                }
                word = nextWord;
            } else if (word.equals("/datetime")) {
                newDateTime = nextWord + " " + commandDetailsParser.next();
            } else {
                throw new DukeException("Please specify your field to change.");
            }
        }

        String[] newDetails = new String[2];
        newDetails[0] = newDescription;
        newDetails[1] = newDateTime;
        return newDetails;
    }
}
