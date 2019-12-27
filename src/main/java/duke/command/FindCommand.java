package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents a command to find a task by keyword.
 */
public class FindCommand extends Command {

    private String keyWord;

    /**
     * Creates a FindCommand instance with a keyword entered by user.
     * @param key keyword entered by user.
     */
    public FindCommand(String key) {
        this.keyWord = key;
    }

    /**
     * Finds a task in the list of tasks which contains a keyword as part of its description.
     *
     * @param storage the storage storing task data.
     * @param tasks a <code>TaskList</code> instance containing a list of tasks.
     * @throws DukeException Exceptions thrown when there are more than/ less than one keyword specified.
     * @return String representing the response.
     */
    public String execute(Storage storage, TaskList tasks) throws DukeException {
        String response;
        checkKeyword();
        StringBuilder listOfTasks = new StringBuilder("Here are the matching tasks in your list:\n     ");
        boolean isFound = findMatchingItems(tasks, listOfTasks);
        if (isFound) {
            response = removeLineSeparator(listOfTasks).toString();
        } else {
            response = "\u2639 OOPS!!! There is no matching tasks in your list.";
        }
        return response;
    }

    /**
     * Checks whether keyword is in the proper format.
     *
     * @throws DukeException Exceptions thrown when there are more than/ less than one keyword specified.
     */
    private void checkKeyword() throws DukeException {
        boolean containsMultipleKeyword = keyWord.split(" ").length > 1;
        if (containsMultipleKeyword || keyWord.equals(" ")) {
            throw new DukeException("\u2639 OOPS!!! Please include at most/ at least one keyword.");
        }
    }

    /**
     * Removes the last line separator from the list of matching tasks.
     *
     * @param listOfTasks StringBuilder storing the list of tasks containing the keyword.
     * @return StringBuilder without a line separator at the end.
     */
    private StringBuilder removeLineSeparator(StringBuilder listOfTasks) {
        int index = listOfTasks.lastIndexOf("\n     ");
        if (index > 0) {
            listOfTasks = new StringBuilder(listOfTasks.substring(0, index));
        }
        return listOfTasks;
    }

    /**
     * Finds all matching tasks in the list of tasks.
     *
     * @param tasks a <code>TaskList</code> instance containing a list of tasks.
     * @param listOfTasks StringBuilder storing the list of tasks containing the keyword.
     * @return a boolean representing whether matching items are found.
     */
    private boolean findMatchingItems(TaskList tasks, StringBuilder listOfTasks) {
        boolean isFound = false;
        for (int i = 0; i < tasks.getSize(); i++) {
            String[] words = tasks.getElement(i).getDescription().split(" ");
            boolean containsKey = false;
            for (String word : words) {
                if (keyWord.equalsIgnoreCase(word)) {
                    containsKey = true;
                    break;
                }
            }
            if (containsKey) {
                isFound = true;
                listOfTasks.append(i + 1).append(".").append(tasks.getElement(i));
                listOfTasks.append("\n     ");
            }
        }
        return isFound;
    }

}
