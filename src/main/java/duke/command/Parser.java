package duke.command;

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
     * @throws ParseException If date is not in date format.
     */
    public void process(String line) throws DukeException, ParseException {
        String[] words = line.split(" ", 2);
        String command = words[0];
        switch (command) {
        case "list":
            ui.printList(taskList.list);
            break;
        case "reminder":
            ui.printReminder(taskList.list);
            break;
        case "done":
            processDone(words);
            break;
        case "todo":
            processToDo(words);
            break;
        case "deadline":
            processDeadline(words);
            break;
        case "event":
            processEvent(words);
            break;
        case "delete":
            processDelete(words);
            break;
        case "find":
            processFind(words);
            break;
        default:
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public void processDone(String[] w) throws DukeException{
        if (w.length < 2) {
            throw new DukeException("OOPS!!! The description of a done cannot be empty.");
        }
        int n = Integer.parseInt(w[1]);
        if (n > taskList.getSize()) {
            throw new DukeException("There is no task number " + n + " in the list.");
        }
        Task currTask = taskList.list.get(n - 1);
        currTask.setStatusIcon(true);
        ui.printDone(currTask);
    }

    public void processToDo(String[] w) throws DukeException {
        if (w.length < 2) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
        String desc = w[1];
        Task currTask = new ToDos(desc);
        taskList.add(currTask);
        ui.printAdd(currTask, taskList);
    }

    public void processDeadline(String[] w) throws DukeException, ParseException {
        if (w.length < 2) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        }
        int before = taskList.getSize();
        String desc = w[1].split(" /by ", 2)[0];
        String time = w[1].split(" /by ", 2)[1];
        Task currTask = new Deadline(desc, time);
        taskList.add(currTask);
        assert taskList.getSize() == (before + 1) : "taskList size not incremented.";
        ui.printAdd(currTask, taskList);
    }

    public void processEvent(String[] w) throws DukeException, ParseException {
        if (w.length < 2) {
            throw new DukeException("OOPS!!! The description of a event cannot be empty.");
        }
        String desc = w[1].split(" /at ", 2)[0];
        String time = w[1].split(" /at ", 2)[1];
        Task currTask = new Event(desc, time);
        taskList.add(currTask);
        ui.printAdd(currTask, taskList);
    }

    public void processDelete(String[] w) {
        Task currTask = taskList.delete(w[1]);
        ui.printDelete(currTask, taskList);
    }

    public void processFind(String[] w) {
        ArrayList<Task> temp = new ArrayList<>();
        for (int i = 0; i < taskList.list.size(); i++) {
            String currDesc = taskList.list.get(i).getDesc();
            if (currDesc.contains(w[1])) {
                temp.add(taskList.list.get(i));
            }
        }
        ui.printFind(temp);
    }
}