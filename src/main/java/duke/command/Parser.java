package duke.command;

import duke.exceptions.DukeDuplicateException;
import duke.exceptions.DukeIllegalDescriptionException;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

/**
 * This is a class to make sense of the user commands and allow duke.Duke to take different actions
 * depending on the commands received.
 */
public class Parser {

    private String[] actions;

    /**
     * Constructor of the Parser class.
     *
     * @param actions A string array contains split commands.
     */
    public Parser(String[] actions) {
        this.actions = actions;
    }

    /**
     * To make amendments according to the first index of array which is the command type.
     *
     * @throws DukeIllegalDescriptionException To deal with the illegal and invalid inputs from users.
     */
    public String parse() throws DukeIllegalDescriptionException, DukeDuplicateException {
        String output = "";
        switch (Command.valueOf(actions[0])) {
        case bye:
            output = commandBye();
            break;
        case list:
            output = commandList();
            break;
        case done:
            output = commandDone();
            break;
        case todo:
            output = commandTodo();
            break;
        case deadline:
            output = commandDeadline();
            break;
        case event:
            output = commandEvent();
            break;
        case delete:
            output = commandDelete();
            break;
        case find:
            output = commandFind();
            break;
        case clear:
            output = commandClear();
            break;
        default:
            break;
        }
        return output;
    }

    /**
     * Action taken when the command is bye.
     */
    private String commandBye() {
        Ui.setFlag();
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Action taken when the command is list.
     */
    private String commandList() {
        String output = "";
        output += "Here are the tasks in your list:\n";
        for (int i = 0; i < TaskList.getList().size(); ++i) {
            output += (i + 1 + "." + TaskList.getList().get(i) + "\n");
        }
        return output;
    }

    /**
     * Action taken when the command is done.
     */
    private String commandDone() throws DukeIllegalDescriptionException {
        String output = "";
        try {
            Storage storage = new Storage();
            int num = Integer.parseInt(actions[1]);
            assert num >= 0 : "The input number is invalid.";
            Task newTask = TaskList.getList().get(num - 1);
            newTask.markAsDone();
            TaskList.getList().set(num - 1, newTask);
            output += "Nice! I've marked this task as done:\n";
            output += TaskList.getList().get(num - 1);
            boolean isAppend = false;
            for (Task task : TaskList.getList()) {
                storage.appendToFile(storage.getFilePath(), task.toString(), isAppend);
                if (!isAppend) {
                    isAppend = true;
                }
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeIllegalDescriptionException(actions[0]);
        }
        return output;
    }

    /**
     * Action taken when the command is to_do.
     *
     * @throws DukeIllegalDescriptionException To deal with the illegal input after to_do command such as no input.
     */
    private String commandTodo() throws DukeIllegalDescriptionException, DukeDuplicateException {
        checkDuplicateTodo(actions[1]);
        String output = "";
        try {
            Ui ui = new Ui();
            Task todo = new Todo(actions[1]);
            TaskList.getList().add(todo);
            output += ui.printAddTask();
            output += (todo + "\n");
            output += ui.printCountTasks();
            Storage storage = new Storage();
            storage.appendToFile(storage.getFilePath(), todo.toString(), true);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeIllegalDescriptionException(actions[0]);
        }
        return output;
    }

    /**
     * Action taken when the command is deadline.
     *
     * @throws DukeIllegalDescriptionException To deal with the illegal input after deadline such as
     *                                         without stating /by time.
     */
    private String commandDeadline() throws DukeIllegalDescriptionException, DukeDuplicateException {
        String output = "";
        try {
            String[] dl = actions[1].split(" /by ");
            String ddlTime = dl[1];
            assert !ddlTime.isEmpty() : "The input time is invalid.";
            SimpleDateFormat ddlFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
            Date ddlDate = ddlFormat.parse(ddlTime);
            Ui ui = new Ui();
            String newDdlTime = ui.getNewFormatDeadline().format(ddlDate);
            checkDuplicate(dl[0], newDdlTime);
            Task deadline = new Deadline(dl[0], newDdlTime);
            TaskList.getList().add(deadline);
            output += ui.printAddTask();
            output += (deadline + "\n");
            output += ui.printCountTasks();
            Storage storage = new Storage();
            storage.appendToFile(storage.getFilePath(), deadline.toString(), true);
        } catch (ArrayIndexOutOfBoundsException | ParseException e) {
            throw new DukeIllegalDescriptionException(actions[0]);
        }
        return output;
    }

    /**
     * Action taken when the command is event.
     *
     * @throws DukeIllegalDescriptionException To deal with the illegal input after event such as
     *                                         without stating /at time.
     */
    private String commandEvent() throws DukeIllegalDescriptionException, DukeDuplicateException {
        String output = "";
        try {
            String[] ev = actions[1].split(" /at ");
            String eventTime = ev[1];
            assert !eventTime.isEmpty() : "The input time is invalid.";
            String[] eventSplit = eventTime.split("-");
            String eventStart = eventSplit[0];
            String eventEnd = eventSplit[1];
            SimpleDateFormat eventFormatStart = new SimpleDateFormat("dd/MM/yyyy HHmm");
            SimpleDateFormat eventFormatEnd = new SimpleDateFormat("HHmm");
            Date eventDateStart = eventFormatStart.parse(eventStart);
            Date eventDateEnd = eventFormatEnd.parse(eventEnd);
            Ui ui = new Ui();
            String newEventTime = ui.getNewFormatEvStart().format(eventDateStart)
                    + " to " + ui.getNewFormatEvEnd().format(eventDateEnd);
            checkDuplicate(ev[0], newEventTime);
            Task event = new Event(ev[0], newEventTime);
            TaskList.getList().add(event);
            output += ui.printAddTask();
            output += (event + "\n");
            output += ui.printCountTasks();
            Storage storage = new Storage();
            storage.appendToFile(storage.getFilePath(), event.toString(), true);
        } catch (ArrayIndexOutOfBoundsException | ParseException e) {
            throw new DukeIllegalDescriptionException(actions[1]);
        }
        return output;
    }

    /**
     * Action taken when the command is delete.
     */
    private String commandDelete() throws DukeIllegalDescriptionException {
        String output = "";
        try {
            Storage storage = new Storage();
            int delNum = Integer.parseInt(actions[1]) - 1;
            assert delNum > 0 : "The input number is invalid.";
            Task delTask = TaskList.getList().get(delNum);
            TaskList.getList().remove(delNum);
            output += ("Noted. I've removed this task:\n" + delTask.toString() + "\n");
            Ui ui = new Ui();
            output += ui.printCountTasks();
            boolean isAppendDel = false;
            for (Task task : TaskList.getList()) {
                storage.appendToFile(storage.getFilePath(), task.toString(), isAppendDel);
                if (!isAppendDel) {
                    isAppendDel = true;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeIllegalDescriptionException(actions[0]);
        }
        return output;
    }

    /**
     * To find the list that contains the keyword inputted by the users and print them out by sequence.
     */
    private String commandFind() throws DukeIllegalDescriptionException {
        String output = "";
        try {
            String keyword = actions[1];
            assert !keyword.isEmpty() : "The keyword to find can not be empty.";
            output += "Here are the matching tasks in your list:\n";
            int count = 1;
            for (Task task : TaskList.getList()) {
                if (task.toString().contains(keyword)) {
                    output += (count + "." + task + "\n");
                    count++;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeIllegalDescriptionException(actions[0]);
        }
        return output;
    }

    private String commandClear() {
        String output = "I have cleared all tasks for you!";
        TaskList.getList().clear();
        Storage storage = new Storage();
        storage.appendToFile(storage.getFilePath(), "", false);
        return output;
    }

    private void checkDuplicateTodo(String string) throws DukeDuplicateException {
        for (Task tasks : TaskList.getList()) {
            if (tasks.toString().contains(string)) {
                throw new DukeDuplicateException();
            }
        }
    }

    private void checkDuplicate(String thing, String time) throws DukeDuplicateException {
        for (Task tasks : TaskList.getList()) {
            if (tasks.toString().contains(thing) && tasks.toString().contains(time)) {
                throw new DukeDuplicateException();
            }
        }
    }

    /**
     * To represent the commands users can input.
     */
    enum Command {
        bye, list, done, todo, deadline, event, delete, find, clear
    }
}
