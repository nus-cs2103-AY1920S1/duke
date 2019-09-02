package duke;

import java.io.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static final SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private static final List<Task> TASKS = new ArrayList<>();

    private static void loadData() throws DukeException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(
                "/home/niclausliu/CS/Java/CS2103/duke/data/duke.txt"));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] task = line.split("`");
            switch (task[0]) {
            case "T":
                TASKS.add(new Todo(task[2], Boolean.parseBoolean(task[1])));
                break;
            case "D":
                TASKS.add(new Deadline(task[2], Boolean.parseBoolean(task[1]), task[3]));
                break;
            case "E":
                TASKS.add(new Event(task[2], Boolean.parseBoolean(task[1]), task[3]));
                break;
            default:
                throw new DukeException("Data file is corrupted");
            }
        }
    }

    private static void saveData() throws IOException {
        FileWriter fileWriter = new FileWriter("/home/niclausliu/CS/Java/CS2103/duke/data/duke.txt");
        for (Task task: TASKS) {
            fileWriter.write(task.toSaveFormat());
        }
        fileWriter.flush();
        fileWriter.close();
    }

    private static void handleList() {
        if (TASKS.size() == 0) {
            System.out.println("     There are no tasks for now!");
            return;
        }
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < TASKS.size(); i++) {
            System.out.printf("     %d.%s\n", i + 1, TASKS.get(i));
        }
    }

    private static void handleDone(String line) throws DukeException {
        try {
            String[] input = line.split(" ");
            Task task = TASKS.get(Integer.parseInt(input[1]) - 1);
            task.markAsDone();
            System.out.println("     Nice! I've marked this task as done: ");
            System.out.printf("       %s\n", task);
            saveData();
        } catch (IOException e) {
            throw new DukeException("Oops! The task is not updated.");
        } catch (Exception e) {
            throw new DukeException("Oops! Please enter a valid task number.");
        }
    }

    private static void handleDelete(String line) throws DukeException {
        try {
            String[] input = line.split(" ");
            Task task = TASKS.get(Integer.parseInt(input[1]) - 1);
            TASKS.remove(task);
            saveData();
            System.out.println("     Noted. I've removed this task: ");
            System.out.printf("       %s\n", task);
            System.out.printf("     Now you have %d tasks in the list.\n", TASKS.size());
        } catch (IOException e) {
            throw new DukeException("Oops! The task is not deleted.");
        } catch (Exception e) {
            throw new DukeException("Oops! Please enter a valid task number.");
        }
    }

    private static void handleToDo(String line) throws DukeException {
        String[] input = line.split(" ");
        String description = "";
        for (int i = 1; i < input.length; i++) {
            description = description.concat(" " + input[i]);
        }
        description = description.trim();
        if (description.isBlank()) {
            throw new DukeException("Oops! The task description cannot be empty.");
        }
        Task task = new Todo(description);
        TASKS.add(task);
        System.out.println("     Got it. I've added this task: ");
        System.out.printf("       %s\n", task);
        System.out.printf("     Now you have %d tasks in the list.\n", TASKS.size());
        try {
            saveData();
        } catch (IOException e) {
            throw new DukeException("Oops! The task is not added.");
        }
    }

    private static void handleDeadLine(String line) throws DukeException {
        String[] input = line.split(" ");
        String description = "";
        String by = "";
        boolean isDescription = true;
        for (String word: input) {
            if (word.equals("deadline")) {
                continue;
            } else if (word.equals("/by")) {
                isDescription = false;
            } else if (isDescription) {
                description = description.concat(" " + word);
            } else {
                by = by.concat(" " + word);
            }
        }
        description = description.trim();
        by = by.trim();
        if (description.isBlank()) {
            throw new DukeException("Oops! The task description cannot be empty.");
        }
        if (by.isBlank()) {
            throw new DukeException("Oops! The task deadline cannot be empty.");
        }
        Task task = new Deadline(description, by);
        TASKS.add(task);
        System.out.println("     Got it. I've added this task: ");
        System.out.printf("       %s\n", task);
        System.out.printf("     Now you have %d tasks in the list.\n", TASKS.size());
        try {
            saveData();
        } catch (IOException e) {
            throw new DukeException("Oops! The task is not added.");
        }
    }

    private static void handleEvent(String line) throws DukeException {
        String[] input = line.split(" ");
        String description = "";
        String at = "";
        boolean isDescription = true;
        for (String word: input) {
            if (word.equals("event")) {
                continue;
            } else if (word.equals("/at")) {
                isDescription = false;
            } else if (isDescription) {
                description = description.concat(" " + word);
            } else {
                at = at.concat(" " + word);
            }
        }
        description = description.trim();
        at = at.trim();
        if (description.isBlank()) {
            throw new DukeException("Oops! The task description cannot be empty.");
        }
        if (at.isBlank()) {
            throw new DukeException("Oops! The task dates/times cannot be empty.");
        }
        Task task = new Event(description, at);
        TASKS.add(task);
        System.out.println("     Got it. I've added this task: ");
        System.out.printf("       %s\n", task);
        System.out.printf("     Now you have %d tasks in the list.\n", TASKS.size());
        try {
            saveData();
        } catch (IOException e) {
            throw new DukeException("Oops! The task is not added.");
        }
    }

    private static void handleException() {
        System.out.println("     Oops! I'm sorry. I don't know what that means.");
    }

    /**
     * Main method of duke project.
     */
    public static void main(String[] args) throws IOException, DukeException {
        Scanner scanner = new Scanner(System.in);

        loadData();
        String greeting = "     Hello! I'm Duke\n"
                + "     What can I do for you?";
        System.out.println(greeting);

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if (line.equals("bye")) {
                System.out.println("     Bye. Hope to see you again soon!");
                break;
            }
            if (line.equals("list")) {
                handleList();
                continue;
            }
            String[] input = line.split(" ");
            try {
                switch (input[0]) {
                case "done":
                    handleDone(line);
                    break;
                case "delete":
                    handleDelete(line);
                    break;
                case "todo":
                    handleToDo(line);
                    break;
                case "deadline":
                    handleDeadLine(line);
                    break;
                case "event":
                    handleEvent(line);
                    break;
                default:
                    throw new DukeException("Oops! I don't understand your command.");
                }
            } catch (DukeException e) {
                System.out.println("     " + e.getMessage());
            }
        }
    }
}
