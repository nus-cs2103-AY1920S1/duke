import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Duke {
    public static ArrayList<Task> list = new ArrayList<Task>();
    static String filePath = "/Users/michelleyong/Desktop/CS2103T/duke/data/duke.txt";

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

    private static void copyFileToList() throws FileNotFoundException {
        File file = new File(filePath);
        Scanner fs = new Scanner(file);
        while (fs.hasNext()) {
            String str = fs.nextLine();
            String[] strArr = str.split(" \\| ");
            if (strArr[0].equals("T")) {
                Todo todo = new Todo(strArr[2]);
                markTaskAsDone(strArr[1], todo);
                list.add(todo);
            } else if (strArr[0].equals("D")) {
                Deadline deadline = new Deadline(strArr[2], strArr[3]);
                markTaskAsDone(strArr[1], deadline);
                list.add(deadline);
            } else if (strArr[0].equals("E")) {
                Event event = new Event(strArr[2], strArr[3]);
                markTaskAsDone(strArr[1], event);
                list.add(event);
            }
        }
        fs.close();
    }

    private static void markTaskAsDone(String str, Task task) {
        if (str.equals("1")) {
            task.markAsDone();
        }
    }

    private static void printDetails(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    private static void copyNewListToFile() throws FileNotFoundException, IOException {
        StringBuffer text = new StringBuffer();
        for (Task t : list) {
            String type = t.getType();
            text.append(type);
            text.append(" | ");
            text.append(t.getStatusNum());
            text.append(" | ");
            text.append(t.getDescription());
            if (type.equals("D") || type.equals("E")) {
                text.append(" | ");
                text.append(t.getDate());
            }
            text.append("\n");
        }
        writeToFile(text.toString());
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
                    String textToAdd = "T | " + todo.getStatusNum() + " | " + todo.getDescription() + "\n";
                    appendToFile(textToAdd);
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
                    String textToAdd = "D | " + deadline.getStatusNum() + " | " + deadline.getDescription() + " | "
                            + deadline.getDate() + "\n";
                    appendToFile(textToAdd);
                } catch (DukeException error) {
                    System.out.println("OOPS!!! The description of a deadline cannot be empty.");
                }
            } else if (s[0].equals("event")) {
                try {
                    if (text.length() <= 5) {
                        throw new DukeException();
                    }
                    String[] t = text.split("/");
                    Event event = new Event(t[0].substring(6, t[0].length() - 1), t[1].substring(3));
                    list.add(event);
                    printDetails(event);
                    String textToAdd = "E | " + event.getStatusNum() + " | " + event.getDescription() + " | "
                            + event.getDate() + "\n";
                    appendToFile(textToAdd);
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
                copyNewListToFile();
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
                    copyNewListToFile();
                } catch (DukeException error) {
                    System.out.println("OOPS!!! There is no such task.");
                }
            } else {
                try {
                    throw new DukeException();
                } catch (DukeException error) {
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :(");
                }
            }
        }
        sc.close();
    }
}