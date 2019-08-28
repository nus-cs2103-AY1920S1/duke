import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.text.ParseException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static String line = "    ____________________________________________________________";

    private static String path = "data.txt";

    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static String[] lists;

    // print a list of strings with horizontal lines and indentation
    private static void formatPrint(String[] lists) {
        Duke.lists = lists;
        System.out.println(line);
        for (String s : lists) {
            System.out.println("     " + s);
        }
        System.out.println(line);
    }

    // print a string with horizontal lines and indentation
    private static void formatPrint(String s) {
        System.out.println(line);
        System.out.println("     " + s);
        System.out.println(line);
    }

    private static String format(String s) {
        return "     " + s;
    }

    // echos with input string
    private static void echo(String s) {
        formatPrint(s);
    }

    private static void write(ArrayList<Task> tasks) {
        try(FileWriter fileWriter = new FileWriter(path, false)) {
            StringBuilder content = new StringBuilder();
            for (int i = 0; i < tasks.size(); i++) {
                Task t = tasks.get(i);
                String type = t instanceof Deadline ? "D"
                        : t instanceof Event ? "E" : "T";
                String isDone = t.isDone ? "1" : "0";
                String taskData = type + "@" + isDone + "@" + t.description;
                content.append(taskData).append("\n");
            }
            fileWriter.write(content.toString());
        } catch (IOException e) {
            formatPrint("Cannot write tasks to file " + path);
        }
    }

    private static ArrayList<Task> read() {
        ArrayList<Task> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("@");
                Task newTask;
                boolean isDone = parts[1].equals("1");
                switch (parts[0]) {
                    case "T":
                        newTask = new Todo(parts[2], isDone);
                        break;
                    case "E":
                        newTask = new Event(parts[2], isDone);
                        break;
                    case "D":
                        newTask = new Deadline(parts[2], isDone);
                        break;
                    default:
                        newTask = new Task("");
                }
                list.add(newTask);
            }
        } catch (IOException e) {
            formatPrint("Cannot read tasks from file " + path);
        }
        return list;
    }

    public static void main(String[] args) throws IOException {
        // initialize objects
        Scanner sc = new Scanner(System.in);
        // String[] list = new String[100];
        ArrayList<Task> tasks = read();

        // create data file if it doesn't exist
        File file = new File(path);
        if (file.createNewFile()) {
            formatPrint("File has been created");
        } else {
            formatPrint("File already exists");
        }

        // greetings
        String[] greetings = {"Hello! I'm Duke", "What can I do for you?"};
        formatPrint(greetings);

        // interacts until the input is "bye"
        while (true) {
            try {
                // read input line
                String s = sc.nextLine();
                if (s.equals("bye")) { // exit
                    formatPrint("Bye. Hope to see you again soon!");
                    break;
                } else if (s.equals("list")) { // show all tasks
                    if (tasks.size() == 0) {
                        throw new DukeException("There is no task in the list.");
                    }
                    System.out.println(line);
                    System.out.println(format("Here are the tasks in your list:"));
                    for (int i = 0; i < tasks.size(); i++) {
                        Task t = tasks.get(i);
                        System.out.println("  " + format(i + 1 + "." + t.repr()));
                    }
                    System.out.println(line);
                } else if (s.split(" ")[0].equals("done")) { // mark as done
                    try {
                        int num = Integer.parseInt(s.split(" ")[1]);
                        if (num >= tasks.size() || num < 1) {
                            throw new DukeException("Task number out of range.");
                        }
                        tasks.get(num - 1).isDone = true;
                        String[] listToPrint = {"Nice! I've marked this task as done: ", "  " + tasks.get(num - 1).repr()};
                        formatPrint(listToPrint);
                    } catch (NumberFormatException ex) {
                        throw new DukeException("Task number should be integer.");
                    }
                    write(tasks);
                } else if (s.split(" ")[0].equals("delete")) { // delete a specific task
                    try {
                        int num = Integer.parseInt(s.split(" ")[1]);
                        if (num >= tasks.size() || num < 1) {
                            throw new DukeException("Task number out of range.");
                        }
                        Task t = tasks.get(num - 1);
                        System.out.println(line);
                        System.out.println(format("Noted. I've removed this task: "));
                        System.out.println("  " + format(t.repr()));
                        tasks.remove(num - 1);
                        switch (tasks.size()) {
                            case 0:
                                System.out.println(format("Now you have no task in the list."));
                                break;
                            case 1:
                                System.out.println(format("Now you have 1 task in the list."));
                                break;
                            default:
                                System.out.println(format("Now you have "  + tasks.size() + " tasks in the list."));
                                break;
                        }
                        System.out.println(line);
                    } catch (NumberFormatException ex) {
                        throw new DukeException("Task number should be integer.");
                    }
                    write(tasks);
                } else { // add new task
                    if (s.split(" ").length == 1) {
                        String type = s.split(" ")[0];
                        switch (type) {
                            case "todo":
                                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                            case "event":
                                throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                            case "deadline":
                                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                            default:
                                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                        }
                    }
                    String type = s.substring(0, s.indexOf(" "));
                    String description = s.substring(s.indexOf(" ") + 1);
                    Task newTask;
                    switch (type) {
                        case "todo":
                            newTask = new Todo(description);
                            break;
                        case "event":
                            newTask = new Event(description);
                            break;
                        case "deadline":
                            newTask = new Deadline(description);
                            break;
                        default:
                            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                    try {
                        String str = format(newTask.repr());
                        tasks.add(newTask);
                        System.out.println(line);
                        System.out.println(format("Got it. I've added this task: "));
                        System.out.println("  " + str);
                    } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException | ParseException ex) {
                        throw new DukeException("Task description should be of format \"context /prep dd/MM/yyyy HHmm\"");
                    }
                    if (tasks.size() == 1) {
                        System.out.println(format("Now you have 1 task in the list."));
                    } else {
                        System.out.println(format("Now you have "  + tasks.size() + " tasks in the list."));}
                    System.out.println(line);
                }
                write(tasks);
            } catch (DukeException ex) {
                formatPrint(ex.getMessage());
            } catch (ParseException e) {
                formatPrint("Task description should be of format \"context /prep dd/MM/yyyy HHmm\"");
            }
        }
    }
}
