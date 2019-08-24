import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static final String SAVE_PATH = "duke.txt";

    public static void main(String[] args) {
        ArrayList<Task> tasks;

        try {
            tasks = Duke.loadTasks();
        } catch (FileNotFoundException ex) {
            tasks = new ArrayList<>();
            try {
                File file = new File(Duke.SAVE_PATH);
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("☹ OOPS!!! There's problem with the IO.");
            }
        }

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        while (!command.equals("bye")) {
            try {
                if (command.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i).toString());
                    }
                    System.out.println();
                    Duke.saveTasks(tasks);
                } else if (command.startsWith("done")) {
                    int index = Integer.parseInt(command.split(" ")[1]);
                    tasks.get(index - 1).markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks.get(index - 1).toString());
                    System.out.println();
                    Duke.saveTasks(tasks);
                } else if (command.startsWith("delete")) {
                    int index = Integer.parseInt(command.split(" ")[1]);
                    Task task = tasks.remove(index - 1);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(task.toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println();
                    Duke.saveTasks(tasks);
                } else if (command.startsWith("todo")) {
                    try {
                        String description = command.split(" ", 2)[1];
                        Task task = new Todo(description);
                        tasks.add(task);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(task.toString());
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println();
                        Duke.saveTasks(tasks);
                    } catch (IndexOutOfBoundsException e) {
                        throw new EmptyDescriptionException();
                    }
                } else if (command.startsWith("event")) {
                    try {
                        String description = command.split(" ", 2)[1];
                        String at = description.split(" /at ", 2)[1];
                        description = description.split(" /at ", 2)[0];
                        SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/yyyy HHmm");
                        Task task = new Event(description, dateFormat.parse(at));
                        tasks.add(task);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(task.toString());
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println();
                        Duke.saveTasks(tasks);
                    } catch (IndexOutOfBoundsException e) {
                        throw new EmptyDescriptionException();
                    } catch (ParseException e) {
                        System.out.println("☹ OOPS!!! The date is invalid.");
                    }
                } else if (command.startsWith("deadline")) {
                    try {
                        String description = command.split(" ", 2)[1];
                        String by = description.split(" /by ", 2)[1];
                        description = description.split(" /by ", 2)[0];
                        SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/yyyy HHmm");
                        Task task = new Event(description, dateFormat.parse(by));
                        tasks.add(task);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(task.toString());
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println();
                        Duke.saveTasks(tasks);
                    } catch (IndexOutOfBoundsException e) {
                        throw new EmptyDescriptionException();
                    } catch (ParseException e) {
                        System.out.println("☹ OOPS!!! The date is invalid.");
                    }
                } else {
                    throw new InvalidCommandException();
                }
            } catch(InvalidCommandException e) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println();
            } catch(EmptyDescriptionException e) {
                System.out.println("☹ OOPS!!! The description of a task cannot be empty.");
                System.out.println();
            } finally {
                command = scanner.nextLine();
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }

    private static String convertTasksToText(ArrayList<Task> tasks) {
        StringBuilder text = new StringBuilder();
        for (Task task : tasks) {
            text.append(task.toSaveString());
            text.append("\n");
        }
        return text.toString();
    }

    private static Task convertTextToTask(String text) {
        String[] tokens = text.split(" | ");
        String type = tokens[0];
        boolean isDone = tokens[2].equals("1");
        String description = tokens[4];
        Task task;

        if (type.equals("T")) {
            task = new Todo(description);
        } else if (type.equals("E")) {
            task = new Event(description, tokens[6]);
        } else {
            task = new Deadline(description, tokens[6]);
        }

        if (isDone) {
            task.markAsDone();
        }

         return task;
    }

    private static ArrayList<Task> loadTasks() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(Duke.SAVE_PATH);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            Task task = Duke.convertTextToTask(scanner.nextLine());
            tasks.add(task);
        }
        return tasks;
    }

    private static void saveTasks(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(Duke.SAVE_PATH);
            fw.write(Duke.convertTasksToText(tasks));
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
