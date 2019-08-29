import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
    public static ArrayList<Task> list = new ArrayList<Task>();
    public static String filePath = "/Users/michelleyong/Desktop/CS2103T/duke/data/duke.txt";

    private static void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend);
        fw.close();
    }

    public static void copyFileToList() throws FileNotFoundException {
        File file = new File(filePath);
        Scanner fs = new Scanner(file);
        while (fs.hasNext()) {
            String line = fs.nextLine();
            String[] taskArr = line.split(" \\| ");
            String type = taskArr[0];
            if (type.equals("T")) {
                Todo todo = new Todo(taskArr[2]);
                if (taskArr[1].equals("1")) {
                    todo.markAsDone();
                }
                list.add(todo);
            } else if (type.equals("D")) {
                Deadline deadline = new Deadline(taskArr[2], taskArr[3]);
                if (taskArr[1].equals("1")) {
                    deadline.markAsDone();
                }
                list.add(deadline);
            } else if (type.equals("E")) {
                Event event = new Event(taskArr[2], taskArr[3]);
                if (taskArr[1].equals("1")) {
                    event.markAsDone();
                }
                list.add(event);
            }
        }
        fs.close();
    }

    public static void printDetails(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    public static String convertTaskToFileFormat(Task task) {
        StringBuffer textToAdd = new StringBuffer();
        String type = task.getType();
        if (type.equals("T")) {
            textToAdd.append("T | ");
        } else if (type.equals("D")) {
            textToAdd.append("D | ");
        } else if (type.equals("E")) {
            textToAdd.append("E | ");
        }
        textToAdd.append(task.getStatusNum());
        textToAdd.append(" | ");
        textToAdd.append(task.getDescription());
        if (type.equals("D") || type.equals("E")) {
            textToAdd.append(" | ");
            textToAdd.append(task.getDate());
        }
        textToAdd.append("\n");
        return textToAdd.toString();
    }

    public static void appendTaskToFile(Task task) throws IOException {
        String textToAppend = convertTaskToFileFormat(task);
        appendToFile(textToAppend);
    }

    public static void updateTaskInFile() throws IOException {
        StringBuffer textToAdd = new StringBuffer();
        for (Task task : list) {
            textToAdd.append(convertTaskToFileFormat(task));
        }
        writeToFile(textToAdd.toString());
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        copyFileToList();

        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        while (sc.hasNext()) {
            String text = sc.nextLine();
            String[] s = text.split(" ");
            if (s[0].equals("todo")) {
                try {
                    if (text.length() <= 4) {
                        throw new DukeException();
                    }
                    String task = text.substring(5);
                    Todo todo = new Todo(task);
                    list.add(todo);
                    printDetails(todo);
                    appendTaskToFile(todo);
                } catch (DukeException error) {
                    System.out.println("OOPS!!! The description of a todo cannot be empty.");
                }
            } else if (s[0].equals("deadline")) {
                try {
                    if (text.length() <= 8) {
                        throw new DukeException();
                    }
                    String[] t = text.split("/");
                    Deadline deadline = new Deadline(t[0].substring(9, t[0].length() - 1), t[1].substring(3));
                    list.add(deadline);
                    printDetails(deadline);
                    appendTaskToFile(deadline);
                } catch (DukeException error) {
                    System.out.println("OOPS!!! The description of a deadline cannot be empty.");
                }
            } else if (s[0].equals("event")) {
                try {
                    if (text.length() <= 5) {
                        throw new DukeException();
                    } String[] t = text.split("/");
                    Event event = new Event(t[0].substring(6, t[0].length() - 1), t[1].substring(3));
                    list.add(event);
                    printDetails(event);
                    appendTaskToFile(event);
                } catch (DukeException error) {
                    System.out.println("OOPS!!! The description of a todo cannot be empty.");
                }
            } else if (s[0].equals("list")) {
                int length = list.size();
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= length; i++) {
                    Task task = list.get(i - 1);
                    System.out.println(i + ". " + task);
                }
            } else if (s[0].equals("done")) {
                int num = Integer.parseInt(s[1]);
                Task task = list.get(num - 1);
                task.markAsDone();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("  " + task);
                updateTaskInFile();
            } else if (s[0].equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (s[0].equals("delete")) {
                try {
                    if (text.length() <= 6) {
                        throw new DukeException();
                    }
                    int num = Integer.parseInt(s[1]);
                    if (num > list.size()) {
                        throw new DukeException();
                    }
                    Task removed = list.remove(num - 1);
                    System.out.println("Noted. I've removed this task: ");
                    System.out.println("  " + removed);
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                    updateTaskInFile();
                } catch (DukeException error) {
                    System.out.println("OOPS!!! There is no such task.");
                }
            } else {
                try {
                    throw new DukeException();
                } catch (DukeException error) {
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
        }
        sc.close();
    }
}