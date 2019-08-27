package duke.command;

import duke.exceptions.DukeIllegalDescriptionException;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;


import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

/**
 * This is a class to make sense of the user commands and allow Duke to take different actions
 * depending on the commands received.
 */
class Parser {

    private String[] action;

    /**
     * Constructor of the Parser class.
     *
     * @param action A string array contains split commands.
     */
    Parser(String[] action) {
        this.action = action;
    }

    /**
     * To make amendments according to the first index of array which is the command type.
     *
     * @throws DukeIllegalDescriptionException To deal with the illegal and invalid inputs from users.
     */
    void parse() throws DukeIllegalDescriptionException {
        switch (Command.valueOf(action[0])) {
        case bye:
            commandBye();
            break;
        case list:
            commandList();
            break;
        case done:
            commandDone();
            break;
        case todo:
            commandTodo();
            break;
        case deadline:
            commandDeadline();
            break;
        case event:
            commandEvent();
            break;
        case delete:
            commandDelete();
            break;
        case find:
            commandFind();
            break;
        }

    }

    /**
     * Action taken when the command is bye.
     */
    private void commandBye() {
        System.out.println("Bye. Hope to see you again soon!");
        Ui.setFlag();
    }

    /**
     * Action taken when the command is list.
     */
    private void commandList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < TaskList.getList().size(); ++i) {
            System.out.println(i + 1 + "." + TaskList.getList().get(i));
        }
    }

    /**
     * Action taken when the command is done.
     */
    private void commandDone() {
        Storage storage = new Storage();
        int num = Integer.parseInt(action[1]);
        Task newTask = TaskList.getList().get(num - 1);
        newTask.markAsDone();
        TaskList.getList().set(num - 1, newTask);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(TaskList.getList().get(num - 1));
        boolean isAppend = false;
        for (Task task : TaskList.getList()) {
            storage.appendToFile(storage.getFilePath(), task.toString(), isAppend);
            if (!isAppend) {
                isAppend = true;
            }
        }
    }

    /**
     * Action taken when the command is to_do.
     *
     * @throws DukeIllegalDescriptionException To deal with the illegal input after to_do command such as no input.
     */
    private void commandTodo() throws DukeIllegalDescriptionException {
        try {
            Ui ui = new Ui();
            Storage storage = new Storage();
            Task todo = new Todo(action[1]);
            TaskList.getList().add(todo);
            ui.printAddTask();
            System.out.println(todo);
            ui.printCountTasks();
            storage.appendToFile(storage.getFilePath(), todo.toString(), true);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeIllegalDescriptionException(action[0]);
        }
    }

    /**
     * Action taken when the command is deadline.
     *
     * @throws DukeIllegalDescriptionException To deal with the illegal input after deadline such as
     *                                         without stating /by time.
     */
    private void commandDeadline() throws DukeIllegalDescriptionException {
        try {
            Ui ui = new Ui();
            Storage storage = new Storage();
            String[] dl = action[1].split(" /by ");
            String ddlTime = dl[1];
            SimpleDateFormat ddlFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
            Date ddlDate = ddlFormat.parse(ddlTime);
            Task deadline = new Deadline(dl[0], ui.getNewFormatDeadline().format(ddlDate));
            TaskList.getList().add(deadline);
            ui.printAddTask();
            System.out.println(deadline);
            ui.printCountTasks();
            storage.appendToFile(storage.getFilePath(), deadline.toString(), true);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeIllegalDescriptionException(action[0]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Action taken when the command is event.
     *
     * @throws DukeIllegalDescriptionException To deal with the illegal input after event such as
     *                                         without stating /at time.
     */
    private void commandEvent() throws DukeIllegalDescriptionException {
        try {
            Ui ui = new Ui();
            Storage storage = new Storage();
            String[] ev = action[1].split(" /at ");
            String eventTime = ev[1];
            String[] eventSplit = eventTime.split("-");
            String eventStart = eventSplit[0];
            String eventEnd = eventSplit[1];
            SimpleDateFormat eventFormatStart = new SimpleDateFormat("dd/MM/yyyy HHmm");
            SimpleDateFormat eventFormatEnd = new SimpleDateFormat("HHmm");
            Date eventDateStart = eventFormatStart.parse(eventStart);
            Date eventDateEnd = eventFormatEnd.parse(eventEnd);
            Task event = new Event(ev[0], ui.getNewFormatEvStart().format(eventDateStart) +
                    " to " + ui.getNewFormatEvEnd().format(eventDateEnd));
            TaskList.getList().add(event);
            ui.printAddTask();
            System.out.println(event);
            ui.printCountTasks();
            storage.appendToFile(storage.getFilePath(), event.toString(), true);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeIllegalDescriptionException(action[1]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Action taken when the command is delete.
     */
    private void commandDelete() {
        Ui ui = new Ui();
        Storage storage = new Storage();
        int delNum = Integer.parseInt(action[1]) - 1;
        Task delTask = TaskList.getList().get(delNum);
        TaskList.getList().remove(delNum);
        System.out.println("Noted. I've removed this task:\n" + delTask.toString());
        ui.printCountTasks();
        boolean isAppendDel = false;
        for (Task task : TaskList.getList()) {
            storage.appendToFile(storage.getFilePath(), task.toString(), isAppendDel);
            if (!isAppendDel) {
                isAppendDel = true;
            }
        }
    }

    /**
     * To find the list that contains the keyword inputted by the users and print them out by sequence.
     */
    private void commandFind() {
        String keyword = action[1];
        System.out.println("Here are the matching tasks in your list:");
        int count = 1;
        for (Task task : TaskList.getList()) {
            if (task.toString().contains(keyword)) {
                System.out.println(count + "." + task);
                count++;
            }
        }
    }


    /**
     * To represent the commands users can input.
     */
    enum Command {
        bye, list, done, todo, deadline, event, delete, find
    }
}
