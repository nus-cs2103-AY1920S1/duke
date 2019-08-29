import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Driver class
 */

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    public void run() {
        // Greet user
        ui.showWelcomeMessage();
        ui.showInitialListMessage(tasks);

        // Ask initial user input
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            String firstWordOfCommand = Parser.getFirstWord(fullCommand);

            if (firstWordOfCommand.equals("bye")) {
                // Bye
                isExit = true;
            } else if (firstWordOfCommand.equals("find")) {
                // Find
                System.out.println("Here are the matching tasks in your list:");
                String keyPhrase = Parser.excludeFirstWord(fullCommand, "find");
                ArrayList<Task> matchingTasks = new ArrayList<>();
                boolean hasMatch = false;
                for (int i = 0; i < tasks.size(); i++) {
                    Task currentTask = tasks.get(i);
                    int currentItemNumber = 0;
                    if (currentTask.getName().contains(keyPhrase)) {
                        hasMatch = true;
                        currentItemNumber += 1;
                        System.out.println(currentItemNumber + "." + currentTask);
                    }
                }
                if (!hasMatch) {
                    System.out.println("Nothing matches :(");
                }

            } else if (firstWordOfCommand.equals("list")) {
                // List
                System.out.println("Here are the tasks in your list:");
                // Output current items in list
                for (int i = 0; i < tasks.size(); i++) {
                    int currentItemNumber = i + 1;
                    Task currentTask = tasks.get(i);
                    System.out.println(currentItemNumber + "." + currentTask);
                }
            } else if (firstWordOfCommand.equals("done")) {
                // Done
                int itemId = Integer.parseInt(Parser.excludeFirstWord(fullCommand, "done"));
                Task currentTask = tasks.get(itemId - 1);
                currentTask.setDone(true);
                System.out.println("Nice! I've marked this task as done:\n[1] " + currentTask.getName());
            } else if (firstWordOfCommand.equals("delete")) {
                // Delete
                int itemId = Integer.parseInt(Parser.excludeFirstWord(fullCommand, "delete"));
                Task currentTask = tasks.get(itemId - 1);
                tasks.remove(currentTask);

                System.out.println("Noted. I've removed this task:");
                System.out.println(currentTask);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else {
                // Add
                try {
                    if (firstWordOfCommand.equals("todo")) {
                        // to do
                        // Remaining words
                        String name = Parser.excludeFirstWord(fullCommand, "todo");
                        if (name.equals("")) {
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        }

                        // Add new task to list
                        Todo newTodo = new Todo(name, false);
                        tasks.add(newTodo);
                    } else if (firstWordOfCommand.equals("deadline")) {
                        // deadline
                        // Remaining words
                        String remainingWords = Parser.excludeFirstWord(fullCommand, "deadline");
                        String name = Parser.splitIntoNameAndTime(remainingWords, " /by ")[0];
                        String dateTime = Parser.splitIntoNameAndTime(remainingWords, " /by ")[1];

                        LocalDateTime localDateTime = Parser.changeToDateTimeFormat(dateTime);

                        // Add new task to list
                        Deadline newDeadline = new Deadline(name, false, dateTime, localDateTime);
                        tasks.add(newDeadline);
                    } else if (firstWordOfCommand.equals("event")) {
                        // event
                        // Remaining words
                        String remainingWords = Parser.excludeFirstWord(fullCommand, "event");
                        String name = Parser.splitIntoNameAndTime(remainingWords, " /at ")[0];
                        String dateTime = Parser.splitIntoNameAndTime(remainingWords, " /at ")[1];

                        LocalDateTime localDateTime = Parser.changeToDateTimeFormat(dateTime);

                        // Add new task to list
                        Event newEvent = new Event(name, false, dateTime, localDateTime);
                        tasks.add(newEvent);
                    } else {
                        // default
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                    // Print output of ADD
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } catch (DukeException e) {
                    ui.showErrorMessage(e);
                }
            }
        }

        // At this point userinput equals "bye"
        // Save data to file
        storage.save(tasks);
        ui.showGoodbyeMessage();
    }

    public static void main(String[] args) {
        new Duke("../data/duke.txt").run();
    }
}
