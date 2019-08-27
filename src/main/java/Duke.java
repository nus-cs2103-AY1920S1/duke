import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static String TASKS_FILE_NAME = System.getProperty("user.dir") + "\\data\\duke.txt";

    public static void main(String[] args) {
        String welcomeMessage = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(welcomeMessage);

        ArrayList<Task> tasks = new ArrayList<>();

        File tasksFile = new File(TASKS_FILE_NAME);
        if (tasksFile.exists()) {
            readTasksFromFile(tasks);
        }

        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();
            String command = getNthWordFromString(input, 0).toLowerCase();

            if (command.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            try {
                switch (command) {
                case "list":
                    printTasks(tasks);
                    break;
                case "done":
                    markTaskDone(tasks, input);
                    break;
                case "delete":
                    deleteTask(tasks, input);
                    break;
                case "todo":
                case "deadline":
                case "event":
                    addTask(tasks, input);
                    break;
                default:
                    System.out.println("I'm sorry, but I don't know what that means :(");
                    break;
                }

            } catch (DukeException e) {
                System.err.println("" + e);
            }
        }
    }

    private static String getNthWordFromString(String input, int n) {
        return input.split(" ")[n];
    }

    private static void printTasks(ArrayList<Task> tasks) {
        System.out.println("Here are the task(s) in your list:");

        String task;
        for (int i = 0; i < tasks.size(); i++) {
            task = String.format("%d.%s", (i + 1), tasks.get(i));
            System.out.println(task);
        }

        if (tasks.size() == 0) {
            System.out.println("There are no tasks in your list right now.");
        }
    }

    private static void markTaskDone(ArrayList<Task> tasks, String input) throws DukeException {
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(getNthWordFromString(input, 1));
            tasks.get(taskNumber - 1).markDone();
        } catch (Exception e) {
            throw new DukeException("Invalid task number.");
        }

        rewriteTasksFile(tasks);

        System.out.println("Nice! I've marked this task as done:");
        System.out.println(String.format("  %s", tasks.get(taskNumber - 1)));
    }

    private static void deleteTask(ArrayList<Task> tasks, String input) throws DukeException {
        int taskNumber;
        Task deletedTask;
        try {
            taskNumber = Integer.parseInt(getNthWordFromString(input, 1));
            deletedTask = tasks.remove(taskNumber - 1);
        } catch (Exception e) {
            throw new DukeException("Invalid task number.");
        }

        rewriteTasksFile(tasks);

        System.out.println("Noted. I've removed this task:");
        System.out.println(String.format("  %s", deletedTask));
        System.out.println(String.format("Now you have %d task(s) in the list.", tasks.size()));
    }

    private static void addTask(ArrayList<Task> tasks, String input) throws DukeException {
        // get task type
        String type = getNthWordFromString(input, 0);
        // get task description
        String description;
        try {
            description = input.split(" ", 2)[1];
        } catch (Exception e) {
            throw new DukeException("Description of " + type + " cannot be empty.");
        }

        Task newTask = null;

        // create new task of specified type
        switch (type) {
        case "todo":
            newTask = new ToDo(description);
            break;
        case "deadline":
            String[] descriptionDeadline = description.split(" /by ", 2);
            if (descriptionDeadline.length < 2) {
                throw new DukeException("Deadline format incorrect, should be e.g. deadline task /by time");
            }
            newTask = new Deadline(descriptionDeadline[0], descriptionDeadline[1]);
            break;
        case "event":
            String[] descriptionTime = description.split(" /at ", 2);
            if (descriptionTime.length < 2) {
                throw new DukeException("Event format incorrect, should be e.g. event task /at time");
            }
            newTask = new Event(descriptionTime[0], descriptionTime[1]);
            break;
        }

        tasks.add(newTask);
        rewriteTasksFile(tasks);

        String message = String.format(
                "Got it. I've added this task:\n  %s\nNow you have %d task(s) in the list.",
                newTask, tasks.size()
        );
        System.out.println(message);
    }

    private static void readTasksFromFile(ArrayList<Task> tasks) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(TASKS_FILE_NAME));

            String line = reader.readLine();
            while (line != null) {
                Task taskToRead = null;

                // get task type and description
                String[] lineArray = line.split(" ~ ");
                String taskType = lineArray[0];
                String taskDescription;

                switch (taskType) {
                case "T":
                    taskDescription = lineArray[2];
                    taskToRead = new ToDo(taskDescription);
                    break;
                case "D":
                    // get description
                    taskDescription = lineArray[2].split("by: ")[0];
                    // remove space and closing bracket
                    taskDescription = taskDescription.substring(0, taskDescription.length() - 2);

                    // get deadline
                    String deadline = lineArray[2].split("by: ")[1];
                    // remove closing bracket
                    deadline = deadline.substring(0, deadline.length() - 1);

                    // read task with deadline
                    taskToRead = new Deadline(taskDescription, deadline);
                    break;
                case "E":
                    // get description
                    taskDescription = lineArray[2].split("at: ")[0];
                    // remove space and closing bracket
                    taskDescription = taskDescription.substring(0, taskDescription.length() - 2);

                    // get time
                    String time = lineArray[2].split("at: ")[1];
                    // remove closing bracket
                    time = time.substring(0, time.length() - 1);

                    // read task with deadline
                    taskToRead = new Event(taskDescription, time);
                    break;
                }

                // check if task is marked as done
                if (lineArray[1].equals("1")) {
                    taskToRead.markDone();
                }

                tasks.add(taskToRead);

                // go to next line (task) in file
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.err.println(e + "");
        } finally {
            // close reader
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.err.println(e + "");
                }
            }
        }
    }

    private static void rewriteTasksFile(ArrayList<Task> tasks) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(TASKS_FILE_NAME));
            // empty tasks file before rewrite
            FileWriter overwrite = new FileWriter(TASKS_FILE_NAME);
            overwrite.close();
            // write all tasks in virtual list to temp file
            for (Task task : tasks) {
                // task string structured as: [D][+] description (by: 2)
                char type = task.toString().toCharArray()[1];
                char done = task.toString().toCharArray()[4] == '+' ? '1' : '0';
                String description = task.toString().split("] ", 2)[1];
                // write task to file
                writer.write(String.format("%s ~ %s ~ %s", type, done, description));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println(e + "");
        } finally {
            // close writer
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    System.err.println(e + "");
                }
            }
        }
    }
}
