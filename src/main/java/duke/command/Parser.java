package duke.main;
import duke.excaptions.IllegalDukeArgumentException;
import duke.excaptions.IllegalDukeFormatException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.text.ParseException;

public class Parser {
    private String[] splitCommand;

    public Parser(String[] splitCommand) {
        this.splitCommand = splitCommand;

    }

    public void parse(Storage storage) throws IllegalDukeFormatException, IllegalDukeArgumentException {
        switch (Command.valueOf(splitCommand[0])) {
        case list:
            commandList();
            break;
        case bye:
            commandBye();
            break;
        case done:
            commandDone(storage);
            break;
        case todo:
            commandTodo(storage);
            break;
        case deadline:
            commandDeadline(storage);
            break;
        case event:
            commandEvent(storage);
            break;
        case delete:
            commandDelete(storage);
            break;
        }
    }
    private void commandList() {
        String tasks = "Here are the tasks in your list:";
        System.out.println(tasks);
        TaskList.print();
    }
    private void commandBye() {
        String Exit = "Bye. Hope to see you again soon!";
        System.out.println(Exit);
        Ui.setIsExit(true);
    }
    private void commandDone(Storage storage) {
        try {
            int index = Integer.parseInt(splitCommand[1]);
            TaskList.getList().get(index - 1).setDone();
            String done = "Nice! I've marked this task as done:\n";
            System.out.println(done + "  " + TaskList.getList().get(index - 1));
            for (Task t : TaskList.getList()) {
                storage.textWrite(t.toString(), false);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Selected index not exists");
        }

    }
    private void commandTodo(Storage storage) throws IllegalDukeArgumentException {
        try {
            String descriptionT = splitCommand[1];
            Task todo = new Todo(descriptionT);
            TaskList.getList().add(todo);
            storage.textWrite(todo.toString(), true);
            System.out.println("Got it. I've added this task:\n" + "  "
                    + todo + "\nNow you have " + TaskList.getList().size() + " tasks in the list.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalDukeArgumentException();
        }

    }
    private void commandDeadline(Storage storage) throws IllegalDukeFormatException {
        try {
            String[] fullCommand = splitCommand[1].split(" /by ");
            String deadlineTime = fullCommand[1];
            Task deadline = new Deadline(fullCommand[0], deadlineTime);
            TaskList.getList().add(deadline);
            storage.textWrite(deadline.toString(), true);
            System.out.println("Got it. I've added this task:\n" + "  "
                    + deadline + "\nNow you have " +
                    TaskList.getList().size() + " tasks in the list.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalDukeFormatException("deadline");
        } catch (ParseException e) {
            System.out.println("OOPS!!! Wrong time format!");
        }
    }
    private void commandEvent(Storage storage) throws IllegalDukeFormatException {
        try {
            String[] fullCommand = splitCommand[1].split(" /at ");
            String eventTime = fullCommand[1];
            Task event = new Event(fullCommand[0], eventTime);
            TaskList.getList().add(event);
            storage.textWrite(event.toString(), true);
            System.out.println("Got it. I've added this task:\n" + "  "
                    + event + "\nNow you have " +
                    TaskList.getList().size() + " tasks in the list.");
        } catch (ParseException e) {
            System.out.println("OOPS!!! Wrong time format!");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalDukeFormatException("event");
        }
    }
    private void commandDelete(Storage storage) {
        try {
            int indexD = Integer.parseInt(splitCommand[1]) - 1;
            Task removedTask = TaskList.getList().remove(indexD);
            for (Task t : TaskList.getList()) {
                storage.textWrite(t.toString(), false);
            }
            System.out.println("Noted. I've removed this task: \n" + "  "
                    + removedTask + "\nNow you have " + TaskList.getList().size() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Selected index not exists");
        }

    }

}



enum Command {
    list,bye,done,todo,deadline,event,delete;
}
