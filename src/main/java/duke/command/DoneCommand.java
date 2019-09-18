package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;

import java.util.Arrays;
import java.util.Collections;
import java.util.InputMismatchException;

/**
 * Represents a command which changes duke.task.Task specified in an index in the task list to be done.
 *
 * @see TaskList
 * @see Task
 */

public class DoneCommand extends Command {
    /**
     * Constructor for duke.command.DoneCommand.
     *
     * @param commandSplitBySpaces String representation of the user input
     */
    public DoneCommand(String[] commandSplitBySpaces) {
        super(commandSplitBySpaces);
    }

    /**
     * Executes the command by using the three arguments provided.
     *
     * @param taskList taskList used to store tasks
     * @param ui User Interface
     * @param storage Storage
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            String outputString = "";
            Integer[] numbers;
            numbers = convertToInteger(commandSplitBySpaces[1].split(","));
            Arrays.sort(numbers, Collections.reverseOrder());

            outputString = outputString + ui.printDoneMessage();
            for (Integer number : numbers) {
                outputString = outputString + ui.printTask(taskList.getTask(number - 1));
                taskList.done(number - 1);
            }
            return outputString;

        } catch (IndexOutOfBoundsException e) {
            throw new InputMismatchException("I'm sorry, the task number does not exist?");
        } catch (NumberFormatException e) {
            throw new InputMismatchException("I'm sorry, can you please write a number after done?");
        }
    }

    /**
     * Converts a string array to an Integer array.
     *
     * @param stringArr String representative of the array
     * @return Integer representative of the string array
     */
    private Integer[] convertToInteger(String[] stringArr) {
        Integer[] index = new Integer[stringArr.length];
        for (int i = 0; i < stringArr.length; i++) {
            index[i] = Integer.parseInt(stringArr[i]);
        }
        return index;
    }
}
