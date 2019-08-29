import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Duke {
    static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    static final String pathToHardDisk = "/data/duke.txt";

    private static File getHardDiskFile() {
        File file = new File(System.getProperty("user.dir") + pathToHardDisk);
        file.getParentFile().mkdirs();
        return file;
    }

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
            saveToHardDisk(taskManager);
        } else if (instruction.matches("^delete \\d+$")) {
            parseDeleteInstruction(taskManager, instruction);
            saveToHardDisk(taskManager);
        } else if (instruction.startsWith("deadline")) {
            taskManager.addTask(parseDeadlineTask(instruction));
            saveToHardDisk(taskManager);
        } else if  (instruction.startsWith("todo")) {
            taskManager.addTask(parseTodoTask(instruction));
            saveToHardDisk(taskManager);
        } else if (instruction.startsWith("event")) {
            taskManager.addTask(parseEventTask(instruction));
            saveToHardDisk(taskManager);
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }

        return true;
    }

    private static void saveToHardDisk(TaskManager taskManager) throws DukeException {
        try {
            FileWriter writer = new FileWriter(getHardDiskFile());
            writer.write(taskManager.printTasksForHardDisk());
            writer.close();
        } catch (IOException ex) {
            throw new DukeException("There is an issue in updating duke.txt.");
        }
    }

    /**
     * Initializes task manager with data from harddisk
     * 
     * @return an initialized Task Manager
     */
    private static TaskManager initializeTaskManager() {
        try {
            ArrayList<Task> tasks = new ArrayList<Task>();

            File file = getHardDiskFile();
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] datas = line.split(" \\| ");

                Task task;
                switch (datas[0]) {
                case "E":
                    task = new Event(datas[2], datas[3]);
                    break;
                case "D":
                    task = new Deadline(datas[2], datas[3]);
                    break;
                case "T":
                    task = new Todo(datas[2]);
                    break;
                default:
                    throw new DukeException("Unrecognized tasks");
                }

                if (datas[1].equals("1")) {
                    task.markAsDone();
                }

                tasks.add(task);
            }
            scanner.close();

            return new TaskManager(tasks);
        } catch (FileNotFoundException ex) {
            return new TaskManager();
        } catch (Exception ex) {
            TaskManager.separator();
            System.out.println("Failed to parse duke.txt");
            TaskManager.separator();

            return new TaskManager();
        }
    }

    public static void main(String[] args) {
        TaskManager taskManager = initializeTaskManager();

        greet();

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
