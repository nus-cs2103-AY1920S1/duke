import java.util.Scanner;

public class Duke {
    private TaskList tasks;
    private Storage storage;

    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException ex) {
            TaskList.separator();
            System.out.println(ex);
            TaskList.separator();

            tasks = new TaskList();
        }
    }

    public void run() {
        greet();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            try {
                String instruction = scanner.nextLine();
                if (!parseInstruction(tasks, instruction)) {
                    break;
                }
            } catch (DukeException ex) {
                TaskList.separator();
                System.out.println(ex);
                TaskList.separator();
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    private void greet() {
        TaskList.separator();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        TaskList.separator();
    }

    private void closing() {
        TaskList.separator();
        System.out.println("Bye. Hope to see you again soon!");
        TaskList.separator();
    }

    private Deadline parseDeadlineTask(String instruction) throws DukeException{
        try {
            String suffix = instruction.split(" ", 2)[1];
            String description = suffix.split(" /by ", 2)[0];
            String by = suffix.split(" /by ", 2)[1];
            return new Deadline(description, by);
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException("Deadline task formatting error.");
        }
    }

    private Todo parseTodoTask(String instruction) throws DukeException {
        try {
            String description = instruction.split(" ", 2)[1];
            return new Todo(description);
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
    }

    private Event parseEventTask(String instruction) throws DukeException {
        try {
            String suffix = instruction.split(" ", 2)[1];
            String description = suffix.split(" /at ", 2)[0];
            String at = suffix.split(" /at ", 2)[1];

            return new Event(description, at);
        } catch(IndexOutOfBoundsException ex) {
            throw new DukeException("Event task formatting error.");
        }
    }

    private void parseDoneInstruction(TaskList tasks, String instruction) throws DukeException {
        int index = Integer.parseInt(instruction.split(" ")[1]);
        tasks.markAsDone(index);
    }

    private void parseDeleteInstruction(TaskList tasks, String instruction) throws DukeException {
        int index = Integer.parseInt(instruction.split(" ")[1]);
        tasks.deleteTask(index);
    }

    private boolean parseInstruction(TaskList tasks, String instruction) throws DukeException{
        if (instruction.equals("bye")) {
            closing();
            return false;
        } else if (instruction.equals("list")) {
            tasks.printTasks();
        } else if (instruction.matches("^done \\d+$")) {
            parseDoneInstruction(tasks, instruction);
            storage.save(tasks);
        } else if (instruction.matches("^delete \\d+$")) {
            parseDeleteInstruction(tasks, instruction);
            storage.save(tasks);
        } else if (instruction.startsWith("deadline")) {
            tasks.addTask(parseDeadlineTask(instruction));
            storage.save(tasks);
        } else if  (instruction.startsWith("todo")) {
            tasks.addTask(parseTodoTask(instruction));
            storage.save(tasks);
        } else if (instruction.startsWith("event")) {
            tasks.addTask(parseEventTask(instruction));
            storage.save(tasks);
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }

        return true;
    }
}
