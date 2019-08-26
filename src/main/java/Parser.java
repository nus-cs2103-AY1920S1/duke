import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Parser {

    private String[] action;

    public Parser(String[] action) {
        this.action = action;
    }

    public void parse() throws DukeIllegalDescriptionException {
        switch (Duke.Command.valueOf(action[0])) {
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
            }

    }

    private void commandBye() {
        System.out.println("Bye. Hope to see you again soon!");
        Ui.setFlag(false);
    }

    private void commandList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < TaskList.getList().size(); ++i) {
            System.out.println(i + 1 + "." + TaskList.getList().get(i));
        }
    }

    public void commandDone() {
        Storage storage = new Storage();
        int num = Integer.parseInt(action[1]);
        Task newTask = TaskList.getList().get(num - 1);
        newTask.markAsDone();
        TaskList.getList().set(num - 1, newTask);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(TaskList.getList().get(num - 1));
        boolean isAppend = false;
        for (Task task : TaskList.getList()) {
            storage.appendToFile(Storage.getFilePath(), task.toString(), isAppend);
            if (!isAppend) {
                isAppend = true;
            }
        }
    }

    public void commandTodo() throws DukeIllegalDescriptionException {
        try {
            Ui ui = new Ui();
            Storage storage = new Storage();
            Task todo = new Todo(action[1]);
            TaskList.getList().add(todo);
            ui.printAddTask();
            System.out.println(todo);
            ui.printCountTasks();
            storage.appendToFile(Storage.getFilePath(), todo.toString(), true);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeIllegalDescriptionException(action[0]);
        }
    }

    public void commandDeadline() throws DukeIllegalDescriptionException {
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
            storage.appendToFile(Storage.getFilePath(), deadline.toString(), true);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeIllegalDescriptionException(action[0]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void commandEvent() throws DukeIllegalDescriptionException {
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
            storage.appendToFile(Storage.getFilePath(), event.toString(), true);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeIllegalDescriptionException(action[1]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void commandDelete() {
        Ui ui = new Ui();
        Storage storage = new Storage();
        int delNum = Integer.parseInt(action[1]) - 1;
        Task delTask = TaskList.getList().get(delNum);
        TaskList.getList().remove(delNum);
        System.out.println("Noted. I've removed this task:\n" + delTask.toString());
        ui.printCountTasks();
        boolean isAppendDel = false;
        for (Task task : TaskList.getList()) {
            storage.appendToFile(Storage.getFilePath(), task.toString(), isAppendDel);
            if (!isAppendDel) {
                isAppendDel = true;
            }
        }
    }
}
