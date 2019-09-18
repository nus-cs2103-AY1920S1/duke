package duke.command;

import duke.task.Task;

import java.util.ArrayList;

public class FindEvent {

    /** Found successfully message. */
    private static String matching_message = "Here are the matching tasks in your list:\n";

    static String findEvent(ArrayList<Task> taskList, String[] params) throws DukeException {
        String searchKey = Parser.joinStrings(params);
        if (searchKey.isEmpty()) {
            throw new DukeException("You must specify a keyword to search by.");
        }
        StringBuilder s = new StringBuilder(matching_message);
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).toString().contains(searchKey)) {
                s.append(i + 1).append(".").append(taskList.get(i));
            }
        }
        return s.toString();
    }
}
