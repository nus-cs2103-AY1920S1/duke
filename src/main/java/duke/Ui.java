package duke;

import java.util.ArrayList;
import tasks.Task;

public class Ui {
    public String print(String msg) {
        return msg;
    }

    /**
     * This method is used to print current items in the list to user.
     *
     * @param list the current TaskList object
     * @return lists of tasks in list
     */
    public String printList(ArrayList<Task> list) {
        assert list != null;
        if (list.size() == 0) {
            return "Empty task list!";
        } else {
            assert list.size() > 0;
            String output = "Here are the tasks in your list:";
            for (int i = 1; i <= list.size(); i++) {
                Task t = list.get(i - 1);
                output += ("\n" + i + "." + t);
            }
            return output;
        }
    }

}
