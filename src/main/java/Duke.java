import java.util.Scanner;

public class Duke {
    static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static void greet() {
        TaskManager.separator();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        TaskManager.separator();
    }

    private static void closing() {
        TaskManager.separator();
        System.out.println("Bye. Hope to see you again soon!");
        TaskManager.separator();
    }

    private static Deadline parseDeadlineTask(String instruction) throws DukeException{
        try {
            String suffix = instruction.split(" ", 2)[1];
            String description = suffix.split(" /by ", 2)[0];
            String by = suffix.split(" /by ", 2)[1];
            return new Deadline(description, by);
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException("Deadline task formatting error.");
        }
    }

    private static Todo parseTodoTask(String instruction) throws DukeException {
        try {
            String description = instruction.split(" ", 2)[1];
            return new Todo(description);
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
    }

    private static Event parseEventTask(String instruction) throws DukeException {
        try {
            String suffix = instruction.split(" ", 2)[1];
            String description = suffix.split(" /at ", 2)[0];
            String at = suffix.split(" /at ", 2)[1];

            return new Event(description, at);
        } catch(IndexOutOfBoundsException ex) {
            throw new DukeException("Event task formatting error.");
        }
    }

    private static void parseDoneInstruction(TaskManager taskManager, String instruction) throws DukeException {
        int index = Integer.parseInt(instruction.split(" ")[1]);
        taskManager.markAsDone(index);
    }

    private static void parseDeleteInstruction(TaskManager taskManager, String instruction) throws DukeException {
        int index = Integer.parseInt(instruction.split(" ")[1]);
        taskManager.deleteTask(index);
    }

    private static boolean parseInstruction(TaskManager taskManager, String instruction) throws DukeException{
        if (instruction.equals("bye")) {
            closing();
            return false;
        } else if (instruction.equals("list")) {
            taskManager.printTasks();
        } else if (instruction.matches("^done \\d+$")) {
            parseDoneInstruction(taskManager, instruction);
        } else if (instruction.matches("^delete \\d+$")) {
            parseDeleteInstruction(taskManager, instruction);
        } else if (instruction.startsWith("deadline")) {
            taskManager.addTask(parseDeadlineTask(instruction));
        } else if  (instruction.startsWith("todo")) {
            taskManager.addTask(parseTodoTask(instruction));
        } else if (instruction.startsWith("event")) {
            taskManager.addTask(parseEventTask(instruction));
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
        return true;
    }

    public static void main(String[] args) {
        greet();

        TaskManager taskManager = new TaskManager();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            try {
                String instruction = scanner.nextLine();
                if (!parseInstruction(taskManager, instruction)) {
                    break;
                }
            } catch (DukeException ex) {
                TaskManager.separator();
                System.out.println(ex);
                TaskManager.separator();
            }
        }
        scanner.close();
    }
}
