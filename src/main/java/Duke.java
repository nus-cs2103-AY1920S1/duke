import task.Task;
import task.Todo;
import task.Deadline;
import task.Event;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static ArrayList<Task> tasks = new ArrayList<>();

    private static final String DATA_FILE_TASKS = "./data/duke.txt";

    /**
     * Reads tasks from ./data/duke.txt, creating an empty file there if it doesn't exist.
     */
    private static void readTasks() {
        tasks = new ArrayList<>();
        try {
            File file = new File(DATA_FILE_TASKS);
            file.createNewFile();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\|");
                for (int i = 0; i < data.length; i++) {
                    data[i] = data[i].trim();
                }
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
                    throw new IOException();
                }
                if (data[1].equals("1")) {
                    task.markAsDone();
                }
                tasks.add(task);
            }
            reader.close();
        } catch (IOException ignored) {
        }
    }

    /**
     * Saves tasks into ./data/duke.txt, creating a new file with the output if it doesn't exist.
     */
    private static void saveTasks() {
        try {
            File file = new File(DATA_FILE_TASKS);
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            boolean first = true;
            for (Task task : tasks) {
                writer.write(first ? "" : "\n");
                first = false;
                writer.write(task.toSaveString());
            }
            writer.flush();
            writer.close();
        } catch (IOException ignored) {
        }
    }

    private static void printNumberTasks() {
        String s = tasks.size() == 1 ? "" : "s";
        System.out.println("Now you have " + tasks.size() + " task" + s + " in the list.");
    }

    private static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    private static void finishTask(String posString) throws DukeException {
        try {
            int position = Integer.parseInt(posString) - 1;
            Task task = tasks.get(position);
            task.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + task);
        } catch (NumberFormatException e) {
            throw new DukeException("Your input should be a number.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("There is no task at the given position.");
        }
    }

    private static void deleteTask(String posString) throws DukeException {
        try {
            int position = Integer.parseInt(posString) - 1;
            Task oldTask = tasks.get(position);
            tasks.remove(position);
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + oldTask);
            printNumberTasks();
        } catch (NumberFormatException e) {
            throw new DukeException("Your input should be a number.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("There is no task at the given position.");
        }
    }

    private static void addTask(Task task) {
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        printNumberTasks();
    }

    /**
     * This is the main method and entry point for the Duke program.
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        readTasks();

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            CommandParser cmdParser = new CommandParser(input);
            String cmdInput = cmdParser.getInput();
            String cmdKeyword = cmdParser.getKeyword();
            String cmdArgs = cmdParser.getArgs();
            String cmdBeforeSlashArgs = cmdParser.getBeforeSlashArgs();
            String cmdSlashKeyword = cmdParser.getSlashKeyword();
            String cmdSlashArgs = cmdParser.getSlashArgs();

            try {
                if (cmdInput.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    saveTasks();
                    break;
                } else if (cmdInput.equals("list")) {
                    listTasks();
                } else if (cmdKeyword.equals("done")) {
                    finishTask(cmdArgs);
                } else if (cmdKeyword.equals("delete")) {
                    deleteTask(cmdArgs);
                } else if (cmdKeyword.equals("todo")) {
                    if (cmdArgs == null) {
                        throw new DukeException("The description of a todo cannot be empty.");
                    }
                    addTask(new Todo(cmdArgs));
                } else if (cmdKeyword.equals("event")) {
                    if (cmdSlashKeyword == null || !cmdSlashKeyword.equals("at")) {
                        throw new DukeException("An event must have an 'at' date or time.");
                    }
                    addTask(new Event(cmdBeforeSlashArgs, cmdSlashArgs));
                } else if (cmdKeyword.equals("deadline")) {
                    if (cmdSlashKeyword == null || !cmdSlashKeyword.equals("by")) {
                        throw new DukeException("A deadline must have a 'by' date or time.");
                    }
                    addTask(new Deadline(cmdBeforeSlashArgs, cmdSlashArgs));
                } else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println("☹ OOPS!!! " + e.getMessage());
            }
        }
    }
}
