import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {

    private TaskList tasks;

    private Duke() {
        this.tasks = new TaskList();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        try {
            duke.importTasks("../data/duke.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return;
        } catch (DukeException e) {
            System.out.println(e);
            return;
        }

        // Greet
        String greeting = "Hello! I'm Duke\nWhat can I do for you?\n";
        System.out.println(greeting);

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String command = scanner.nextLine().trim();

            // Exit
            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            try {
                duke.run(command);
            } catch (DukeException e) {
                System.out.println(e);
            }

            // Empty line separator at the end of the command response
            System.out.println();
        }

        scanner.close();
        try {
            duke.exportTasks("../data/duke.txt");
        } catch (IOException e) {
            System.out.println("Failed to save tasks.");
        }
    }

    private void importTasks(String filePath) throws FileNotFoundException, DukeException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String[] data = scanner.nextLine().split(" \\| ");
            Task task;

            switch (data[0]) {
                case "T":
                    task = new Todo(data[2]);
                    break;
                case "D":
                    task = new Deadline(data[2], data[3]);
                    break;
                case "E":
                    task = new Event(data[2], data[3]);
                    break;
                default:
                    throw new DukeException("Failed to load tasks.");
            }

            if (data[1].equals("1")) {
                task.markAsDone();
            }

            tasks.add(task);
        }
        scanner.close();
    }

    private void exportTasks(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : tasks.getTasks()) {
            fw.write(task.serialize());
            fw.write(System.lineSeparator());
        }
        fw.close();
    }

    private void run(String command) throws DukeException {
        if (command.equals("list")) {
            showTasks();
        } else if (command.matches("^done\\s+\\d+$")) {
            int taskId = Integer.parseInt(command.split("\\s+")[1]);
            completeTask(taskId);
        } else if (command.matches("^(todo|deadline|event).*")) {
            addTask(command);
        } else if (command.matches("^delete\\s+\\d+$")) {
            int taskId = Integer.parseInt(command.split("\\s+")[1]);
            deleteTask(taskId);
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private void showTasks() throws DukeException {
        if (tasks.isEmpty()) {
            System.out.println("There are currently no tasks in your list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 1; i <= tasks.size(); i++) {
                Task task = tasks.get(i);
                System.out.printf("%d.%s\n", i, task);
            }
        }
    }

    private void completeTask(int taskId) throws DukeException {
        Task task = tasks.get(taskId);
        task.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.printf("  %s\n", task);
    }

    private void addTask(String command) throws DukeException {
        String[] commandArgs = command.split("\\s+", 2);
        String taskType = commandArgs[0];

        Task newTask;

        switch (taskType) {
        case "todo":
            if (commandArgs.length < 2) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            newTask = new Todo(commandArgs[1]);
            break;
        case "deadline":
            if (commandArgs.length < 2 || commandArgs[1].equals("/by")) {
                throw new DukeException("The description and the due time of a deadline cannot be empty.");
            }
            if (commandArgs[1].startsWith("/by")) {
                throw new DukeException("The description of a deadline cannot be empty.");
            }
            if (!commandArgs[1].contains("/by") || commandArgs[1].endsWith("/by")) {
                throw new DukeException("The due time of a deadline cannot be empty.");
            }
            String[] deadlineArgs = commandArgs[1].split("\\s*/by\\s*", 2);
            newTask = new Deadline(deadlineArgs[0], deadlineArgs[1]);
            break;
        case "event":
            if (commandArgs.length < 2 || commandArgs[1].equals("/at")) {
                throw new DukeException("The description and the time of an event cannot be empty.");
            }
            if (commandArgs[1].startsWith("/at")) {
                throw new DukeException("The description of an event cannot be empty.");
            }
            if (!commandArgs[1].contains("/at") || commandArgs[1].endsWith("/at")) {
                throw new DukeException("The time of an event cannot be empty.");
            }
            String[] eventArgs = commandArgs[1].split("\\s*/at\\s*", 2);
            newTask = new Event(eventArgs[0], eventArgs[1]);
            break;
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
        tasks.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.printf("  %s\n", newTask);
        int total = tasks.size();
        System.out.printf("Now you have %d task%s in the list.\n", total, total > 1 ? "s" : "");
    }

    private void deleteTask(int taskId) throws DukeException {
        Task task = tasks.remove(taskId);
        System.out.println("Noted. I've removed this task:");
        System.out.printf("  %s\n", task);
        int total = tasks.size();
        System.out.printf("Now you have %d task%s in the list.\n", total, total > 1 ? "s" : "");
    }
}
