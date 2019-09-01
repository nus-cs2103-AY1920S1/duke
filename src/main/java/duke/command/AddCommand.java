package duke.command;

import duke.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddCommand extends Command {
    private String command;

    public AddCommand(String command) {
        this.command = command;
    }

    private static Todo addTodo(String task, TaskList tasks) {
        Todo todo = new Todo(task);
        tasks.add(todo);
        return todo;
    }

    private static Deadline addDeadline(String task, TaskList tasks) throws DukeException {
        String[] attr = task.split(" /by ");
        if (Date.matches(attr[1])) {
            Deadline deadline = new Deadline(attr[0], new Date(attr[1]));
            tasks.add(deadline);
            return deadline;
        } else {
            throw new DukeException("☹ OOPS!!! A valid date was not submitted.");
        }
    }

    private static Event addEvent(String task, TaskList tasks) throws DukeException {
        String[] attr = task.split(" /at ");
        if (Date.matches(attr[1])) {
            Event event = new Event(attr[0], new Date(attr[1]));
            tasks.add(event);
            return event;
        } else {
            throw new DukeException("☹ OOPS!!! A valid date was not submitted.");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] commandList = this.command.split(" ");
        String taskType = commandList[0];
        String taskText = String.join(" ", Arrays.copyOfRange(commandList, 1, commandList.length));
        Task task;
        switch (taskType) {
        case "todo":
            if (taskText.isEmpty()) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            task = addTodo(taskText, tasks);
            break;
        case "deadline":
            if (taskText.isEmpty()) {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            task = addDeadline(taskText, tasks);
            break;
        case "event":
            if (taskText.isEmpty()) {
                throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
            }
            task = addEvent(taskText, tasks);
            break;
        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        List<String> list = new ArrayList<>();
        list.add("Got it. I've added this task:");
        list.add(String.format("  %s", task));
        String noun = tasks.getSize() > 1 ? "tasks" : "task";
        list.add(String.format("Now you have %d %s in the list.", tasks.getSize(), noun));
        System.out.println(new Message(list));
    }
}
