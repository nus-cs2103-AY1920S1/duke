package duke.command;

import duke.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDos;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * Deals with making sense of the user command.
 */

public class Parser {
    TaskList taskList;
    Ui ui;
    String line;

    public Parser(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * Deals with processing the user command and taking actions accordingly.
     *
     * @param line line being processed.
     * @throws DukeException  If input is incorrect.
     * @throws ParseException  If date is not in date format.
     */

    public void process(String line) throws DukeException, ParseException {
        Integer num; //number in list which is done
        Task currTask; //refers to current task in list
        String currEvent;
        String desc = ""; //current task being added
        String time = ""; //current time of current task being added
        String[] words = line.split(" ", 2);
        currEvent = words[0];
        if (line.equals("list")) {
            ui.printList(taskList.list);
        } else if (words[0].equals("done")) {
            num = Integer.valueOf(words[1]);
            currTask = taskList.list.get(num - 1);
            currTask.setStatusIcon(true);
            ui.printDone(currTask);
        } else if (currEvent.equals("todo")) {
            if (words.length < 2) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            desc = words[1];
            currTask = new ToDos(desc);
            taskList.add(currTask);
            ui.printAdd(currTask, taskList);
        } else if (currEvent.equals("deadline")) {
            if (words.length < 2) {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            desc = words[1].split(" /by ", 2)[0];
            time = words[1].split(" /by ", 2)[1];
            currTask = new Deadline(desc, time);
            taskList.add(currTask);
            ui.printAdd(currTask, taskList);
        } else if (currEvent.equals("event")) {
            if (words.length < 2) {
                throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
            }
            desc = words[1].split(" /at ", 2)[0];
            time = words[1].split(" /at ", 2)[1];
            currTask = new Event(desc, time);
            taskList.add(currTask);
            ui.printAdd(currTask, taskList);
        } else if (words[0].equals("delete")) {
            currTask = taskList.delete(words[1]);
            ui.printDelete(currTask, taskList);
        } else if (words[0].equals("find")) {
            ArrayList<Task> temp = new ArrayList<>();
            for (int i = 0; i < taskList.list.size(); i++) {
                String currDesc = taskList.list.get(i).getDesc();
                if (currDesc.contains(words[1])) {
                    temp.add(taskList.list.get(i));
                }
            }
            ui.printFind(temp);
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
