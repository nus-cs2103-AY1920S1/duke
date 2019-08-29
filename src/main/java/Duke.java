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
        TaskList.separator();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        TaskList.separator();
    }

    private static void closing() {
        TaskList.separator();
        System.out.println("Bye. Hope to see you again soon!");
        TaskList.separator();
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

    private static void parseDoneInstruction(TaskList tasks, String instruction) throws DukeException {
        int index = Integer.parseInt(instruction.split(" ")[1]);
        tasks.markAsDone(index);
    }

    private static void parseDeleteInstruction(TaskList tasks, String instruction) throws DukeException {
        int index = Integer.parseInt(instruction.split(" ")[1]);
        tasks.deleteTask(index);
    }

    private static boolean parseInstruction(TaskList tasks, String instruction) throws DukeException{
        if (instruction.equals("bye")) {
            closing();
            return false;
        } else if (instruction.equals("list")) {
            tasks.printTasks();
        } else if (instruction.matches("^done \\d+$")) {
            parseDoneInstruction(tasks, instruction);
            saveToHardDisk(tasks);
        } else if (instruction.matches("^delete \\d+$")) {
            parseDeleteInstruction(tasks, instruction);
            saveToHardDisk(tasks);
        } else if (instruction.startsWith("deadline")) {
            tasks.addTask(parseDeadlineTask(instruction));
            saveToHardDisk(tasks);
        } else if  (instruction.startsWith("todo")) {
            tasks.addTask(parseTodoTask(instruction));
            saveToHardDisk(tasks);
        } else if (instruction.startsWith("event")) {
            tasks.addTask(parseEventTask(instruction));
            saveToHardDisk(tasks);
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }

        return true;
    }

    private static void saveToHardDisk(TaskList tasks) throws DukeException {
        try {
            FileWriter writer = new FileWriter(getHardDiskFile());
            writer.write(tasks.printTasksForHardDisk());
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
    private static TaskList initializeTaskList() {
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

            return new TaskList(tasks);
        } catch (FileNotFoundException ex) {
            return new TaskList();
        } catch (Exception ex) {
            TaskList.separator();
            System.out.println("Failed to parse duke.txt");
            TaskList.separator();

            return new TaskList();
        }
    }

    public static void main(String[] args) {
        TaskList tasks = initializeTaskList();

        greet();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            try {
                String instruction = scanner.nextLine();
                if (!parseInstruction(tasks, instruction)) {
                    break;
                }
            } catch (DukeException ex) {
                tasks.separator();
                System.out.println(ex);
                tasks.separator();
            }
        }
        scanner.close();
    }
}
