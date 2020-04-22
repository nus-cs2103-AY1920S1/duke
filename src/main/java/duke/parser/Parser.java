package duke.parser;

import duke.command.Command;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskListHistory;
import duke.task.Todo;

import java.util.Collections;

public class Parser {

    /**
     * Parses the given input, and returns a Duke Command.
     * 
     * @param input a String object.
     * @return a Duke Command.
     */
    public static Command parse(String input) {
        String[] inputs = input.trim().split(" ", 2);
        String command = inputs[0];

        switch (command) {
        case "todo":
            return new Command() {
                public String execute(TaskList tasks, Storage storage) throws DukeException {
                    // Saves a snapshot of the previous tasklist
                    TaskListHistory.getInstance().push(tasks);

                    try {
                        Task task = new Todo(inputs[1]);
                        tasks.add(task);
                        storage.saveTasks(tasks);
                        return createAddedMessage(task, tasks);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("I'm afraid I can't do that. Todo task description cannot be blank.");
                    }
                }
            };
        case "deadline":
            return new Command() {
                public String execute(TaskList tasks, Storage storage) throws DukeException {
                    // Saves a snapshot of the previous tasklist
                    TaskListHistory.getInstance().push(tasks);

                    try {
                        String[] strings = inputs[1].split("/by", 2);
                        String desc = strings[0].trim();
                        String by = strings[1].trim();

                        Task task = new Deadline(desc, by);
                        tasks.add(task);
                        storage.saveTasks(tasks);
                        return createAddedMessage(task, tasks);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("I'm afraid I can't do that. Deadline task description or deadline cannot be blank.");
                    }
                }
            };
        case "event":
            return new Command() {
                public String execute(TaskList tasks, Storage storage) throws DukeException {
                    // Saves a snapshot of the previous tasklist
                    TaskListHistory.getInstance().push(tasks);

                    try {
                        String[] strings = inputs[1].split("/by", 2);
                        String desc = strings[0].trim();
                        String at = strings[1].trim();

                        Task task = new Event(desc, at);
                        tasks.add(task);
                        storage.saveTasks(tasks);
                        return createAddedMessage(task, tasks);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("I'm afraid I can't do that. Event task description or start time cannot be blank.");
                    }
                }
            };
        case "list":
            return new Command() {
                public String execute(TaskList tasks, Storage storage) throws DukeException {
                    if (tasks.isEmpty()) {
                        throw new DukeException("I'm afraid I can't do that. You have no tasks yet.");
                    }
                    int index = 1;
                    String message = "";

                    for (Task task : tasks) {
                        message += String.format("%d.%s\n", index, task);
                        index++;
                    }

                    return message;
                }
            };
        case "done":
            return new Command() {
                public String execute(TaskList tasks, Storage storage) throws DukeException {
                    // Saves a snapshot of the previous tasklist
                    TaskListHistory.getInstance().push(tasks);

                    try {
                        int taskNumber = Integer.parseInt(inputs[1].trim());
                        Task taskDone = tasks.get(taskNumber - 1);
                        taskDone.markAsDone();
                        storage.saveTasks(tasks);

                        return String.format("Nice! I've marked this task as done:\n%s\n", taskDone);
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("I'm afraid I can't do that. Your task cannot be found!");
                    }
                }
            };
        case "delete":
            return new Command() {
                public String execute(TaskList tasks, Storage storage) throws DukeException {
                    // Saves a snapshot of the previous tasklist
                    TaskListHistory.getInstance().push(tasks);

                    try {
                        int taskNumber = Integer.parseInt(inputs[1].trim());
                        Task taskRemoved = tasks.remove(taskNumber - 1);
                        storage.saveTasks(tasks);

                        return String.format(
                                "Got it. I've removed this task:\n" + "%s\n" + "Now you have %d tasks in the list.\n",
                                taskRemoved, tasks.size());
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("I'm afraid I can't do that. Your task cannot be found!");
                    }
                }
            };
        case "find":
            return new Command() {
                public String execute(TaskList tasks, Storage storage) throws DukeException {
                    try {
                        String keyword = inputs[1];
                        TaskList matchingTasks = new TaskList();

                        for (Task task : tasks) {
                            if (task.getDescription().matches(keyword)) {
                                matchingTasks.add(task);
                            }
                        }

                        if (matchingTasks.size() == 0) {
                            throw new DukeException("I'm afraid I can't do that. There is no matching task.");
                        }

                        String output = "Here are the matching tasks in your list:\n";

                        int index = 1;
                        for (Task task : tasks) {
                            output += String.format("%d.%s\n", index, task);
                            index++;
                        }

                        return output;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("I'm afraid I can't do that. I don't know what to search for.");
                    }
                }
            };
        case "bye":
            return new Command() {
                public String execute(TaskList tasks, Storage storage) {
                    return "Goodbye, Dave.";
                }

                public boolean isExit() {
                    return true;
                }
            };
        case "undo":
            return new Command() {
                public String execute(TaskList tasks, Storage storage) throws DukeException {
                    TaskList prevTasks = TaskListHistory.getInstance().pop();

                    if (prevTasks == null) {
                        throw new DukeException("I'm afraid I can't do that. There is nothing to undo.");
                    }

                    tasks.clear();

                    Task[] tasksToAdd = new Task[prevTasks.size()];
                    prevTasks.toArray(tasksToAdd);

                    Collections.addAll(tasks, tasksToAdd);
                    storage.saveTasks(tasks);

                    return "I've undo the previous command.";
                }
            };
        case "help":
            return new Command() {
                public String execute(TaskList tasks, Storage storage) {
                    StringBuilder sb = new StringBuilder();

                    sb.append("Here are some helpful instructions!\n");
                    sb.append("  > todo <description> - Adds a todo task\n");
                    sb.append("  > deadline <description> /by <datetime> - Adds a datetime task, datetime is in <dd/MM/YY HHmm> format\n");
                    sb.append("  > event <description> /at <datetime> - Adds a event task, datetime is in <dd/MM/YY HHmm> format\n");
                    sb.append("  > list - Lists all tasks\n");
                    sb.append("  > find <keyword> - Finds tasks matching the keyword\n");
                    sb.append("  > done <id> - Marks a task with the given id as done\n");
                    sb.append("  > delete <id> - Deletes a task with the given id\n");
                    sb.append("  > undo - Undos a previous todo/deadline/event/done/delete command\n");

                    return sb.toString();
                }
            };
        default:
            return new Command() {
                public String execute(TaskList tasks, Storage storage) throws DukeException {
                    throw new DukeException("I'm afraid I can't do that. You entered an invalid command.");
                }
            };
        }
    }

    private static String createAddedMessage(Task task, TaskList tasks) {
        return String.format("Got it. I've added this task:\n" + "%s\n" + "Now you have %d tasks in the list.\n", task,
                tasks.size());
    }
}