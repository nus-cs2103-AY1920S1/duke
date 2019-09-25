import java.util.Scanner;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.Deadline;
import duke.tasklist.Event;
import duke.tasklist.Task;
import duke.tasklist.TaskList;
import duke.tasklist.Todo;
import duke.ui.Ui;

public class Duke {
    /**
     * Main method of duke.Duke application.
     */

    public static void main(String[] args) {
        // Create a scanner to take in user input
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser();

        String contents = Storage.readFile();

        System.out.println(
                Ui.formatMessage("Hello, I'm duke.Duke\nWhat can I do for you?")
        );

        TaskList tasks = new TaskList();
        String message;

        String[] contentLines = contents.split("\n");

        for (String line : contentLines) {
            String[] lineTokens = line.split(" \\| ");
            if (lineTokens[0].equals("T")) {
                Task newTask = new Todo(lineTokens[2]);
                if (lineTokens[1].equals("1")) {
                    newTask.setCompleted();
                }
                tasks.addTask(newTask);
            } else if (lineTokens[0].equals("D")) {
                Task newTask = new Deadline(lineTokens[2], lineTokens[3]);
                if (lineTokens[1].equals("1")) {
                    newTask.setCompleted();
                }
                tasks.addTask(newTask);
            } else if (lineTokens[0].equals("E")) {
                Task newTask = new Event(lineTokens[2], lineTokens[3]);
                if (lineTokens[1].equals("1")) {
                    newTask.setCompleted();
                }
                tasks.addTask(newTask);
            }
        }

        String command = sc.nextLine();
        String[] commandTokens = command.split(" ");

        while (!parser.isBye(command)) {
            try {
                if (parser.isList(command)) {
                    String list = "Here are the tasks in your list:\n";
                    for (int i = 0; i < tasks.getSize(); i++) {
                        Task task = tasks.getTask(i);
                        list += String.format(
                                "%d." + task,
                                i + 1
                        );
                    }
                    System.out.println(Ui.formatMessage(list));
                } else if (parser.isDone(commandTokens)) {
                    int completedIndex = Integer.parseInt(commandTokens[1]) - 1;
                    message = "Nice! I've marked this task as done:\n  ";
                    Task completedTask = tasks.getTask(completedIndex);
                    completedTask.setCompleted();
                    message += completedTask;
                    System.out.println(Ui.formatMessage(message));
                } else if (parser.isTodo(commandTokens)) {
                    if (commandTokens.length == 1) {
                        throw new Exception("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    Task newTask = new Todo(command.substring(5));
                    tasks.addTask(newTask);
                    message = String.format(
                            "Got it. I've added this task:\n  %sNow you have %d tasks in the list.",
                            newTask,
                            tasks.getSize()
                    );
                    System.out.println(
                            Ui.formatMessage(message)
                    );
                } else if (parser.isDeadline(commandTokens)) {
                    String[] deadlineTokens = command.substring(9).split(" /by ");
                    Task newTask = new Deadline(deadlineTokens[0], deadlineTokens[1]);
                    tasks.addTask(newTask);
                    message = String.format(
                            "Got it. I've added this task:\n  %sNow you have %d tasks in the list.",
                            newTask,
                            tasks.getSize()
                    );
                    System.out.println(
                            Ui.formatMessage(message)
                    );
                } else if (parser.isEvent(commandTokens)) {
                    String[] eventTokens = command.substring(6).split(" /at ");
                    Task newTask = new Event(eventTokens[0], eventTokens[1]);
                    tasks.addTask(newTask);
                    message = String.format(
                            "Got it. I've added this task:\n  %sNow you have %d tasks in the list.",
                            newTask,
                            tasks.getSize()
                    );
                    System.out.println(
                            Ui.formatMessage(message)
                    );
                } else if (parser.isDelete(commandTokens)) {
                    int removeIndex = Integer.parseInt(commandTokens[1]) - 1;
                    Task removeTask = tasks.getTask(removeIndex);
                    tasks.removeTask(removeIndex);
                    message = String.format(
                            "Noted. I've removed this task:\n  %sNow you have %d tasks in the list.",
                            removeTask,
                            tasks.getSize());
                    System.out.println(Ui.formatMessage(message));
                } else if (parser.isFind(commandTokens)) {
                    String keyword = commandTokens[1];
                    TaskList tasksWithKeyword = new TaskList();
                    for (int i = 0; i < tasks.getSize(); i++) {
                        if (tasks.getTask(i).getDescription().contains(keyword)) {
                            tasksWithKeyword.addTask(tasks.getTask(i));
                        }
                    }

                    String list = "Here are the matching tasks in your list:\n";
                    for (int i = 0; i < tasksWithKeyword.getSize(); i++) {
                        Task task = tasks.getTask(i);
                        list += String.format(
                                "%d." + task,
                                i + 1
                        );
                    }
                    System.out.println(Ui.formatMessage(list));

                } else {
                    throw new Exception("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (Exception e) {
                System.out.println(Ui.formatMessage(e.getMessage()));
            }
            command = sc.nextLine();
            commandTokens = command.split(" ");
        }

        Storage.saveFile(tasks);

        System.out.println(
                Ui.formatMessage("Bye. Hope to see you again soon!")
        );
    }


}
