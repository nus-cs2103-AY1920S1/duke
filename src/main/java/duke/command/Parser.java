package duke.command;
import duke.excaptions.IllegalDukeArgumentException;
import duke.excaptions.IllegalDukeFormatException;
import duke.list.TaskList;


import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.text.ParseException;

/**
 * The Parser class is one of the class in command package which used to deal with making sense of the user command
 */
public class Parser {
    private String[] splitCommand;

    /**
     * Constructor to write in command line
     * @param splitCommand
     */
    public Parser(String[] splitCommand) {
        this.splitCommand = splitCommand;

    }

    /**
     * this method is to deal with different commands
     * @param storage the object to interact with
     * @throws IllegalDukeFormatException
     * @throws IllegalDukeArgumentException
     */
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

    /**
     * method for "list" command, print the list out
     */
    private void commandList() {
        String tasks = "Here are the tasks in your list:";
        System.out.println(tasks);
        TaskList.print();
    }
    /**
     * method for "bye" command, print ending and set isExit to true
     */
    private void commandBye() {
        String Exit = "Bye. Hope to see you again soon!";
        System.out.println(Exit);
        Ui.setIsExit(true);
    }

    /**
     * method for "done" command, set the task done, and rewrite them to text file with the status updated.
     * @param storage the object to interact with
     */
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

    /**
     * method for "todo" command, add the todo task into the list and write it in text file
     * @param storage the object to interact with
     * @throws IllegalDukeArgumentException
     */
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

    /**
     * method for "deadline" command, add the deadline task into the list and write it in text file
     * @param storage the object to interact with
     * @throws IllegalDukeFormatException
     */
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

    /**
     * method for "event" command, add the event task into the list and write it in text file
     * @param storage  the object to interact with
     * @throws IllegalDukeFormatException
     */
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

    /**
     * method for "delete" command, delete the task by remove it from the taskList and rewrite list to file
     * @param storage the object to interact with
     */
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


/**
 * the enum class with all types of command
 */
enum Command {
    list,bye,done,todo,deadline,event,delete;
}
