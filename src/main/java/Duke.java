import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static String LIST_PATH = "C:/Users/Yu Han Jeong/Desktop/CS2103T/duke/src/data/duke.txt";

    public static void main(String[] args) {
        greetings();
        Scanner sc = new Scanner(System.in);
        File planner = new File(LIST_PATH);
        ItemsList itemsList = new ItemsList();
        if (planner.exists()) {
            planner.setExecutable(true);
            planner.setWritable(true);
            planner.setWritable(true);
        }
        //Events that change list: mark as done, to-do, events, and deadlines, and deletions
        while (sc.hasNext()) {
            String command = sc.next();
            if (command.equals("list")) {
                itemsList.printList();
            } else if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (command.equals("done")) {
                int itemIndex = sc.nextInt();
                Task task = itemsList.getTaskAtIndex(itemIndex - 1);
                task.markAsDone();
            } else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) { // new item
                addNewItem(command, sc, itemsList, planner);
            } else if (command.equals("delete")) {
                int itemIndex = sc.nextInt();
                itemsList.deleteTask(itemIndex);
                StringBuilder sb = new StringBuilder();
                ArrayList<Task> items = itemsList.getItemsList();
                for (Task task : items) {
                    sb.append(task);
                    sb.append("\n");
                }
                try {
                    writeToFile(planner, sb.toString().trim());
                }
                catch (Exception ex) {
                    System.out.println(ex);
                }
            } else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                sc.nextLine();
            }
        }
    }

    private static void appendToFile(File file, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(file, true); // create a FileWriter in append mode
        fw.write(textToAppend + "\n");
        fw.close();
    }

    private static void writeToFile(File file, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(file);
        fw.write(textToAdd);
        fw.close();
    }

    private static void addNewItem(String command, Scanner sc, ItemsList itemsList, File planner) {
        String item = sc.nextLine().trim();
        if (item.equals("")) {
            System.out.printf("☹ OOPS!!! The description of a %s cannot be empty.\n", command);
            return;
        }
        Task task;
        if (command.equals("todo")) {
            task = new ToDo(item);
        } else if (command.equals("deadline")) {
            try {
                String[] itemSlashDeadline = item.split("/");
                String deadline = itemSlashDeadline[1].substring(3);
                task = new Deadline(itemSlashDeadline[0].trim(), deadline);
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.out.println("Invalid deadline input");
                return;
            }
        } else if (command.equals("event")) {
            try {
                String[] itemsSlashTiming = item.split("/");
                String timing = itemsSlashTiming[1].substring(3);
                task = new Event(itemsSlashTiming[0].trim(), timing);
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.out.println("Invalid event input");
                return;
            }
        } else {
            System.out.println("Task type can only be of type todo, deadline or event");
            return;
        }
        itemsList.addItem(task);
        try {
            appendToFile(planner, task.toString());
        }
        catch (IOException ex) {
            System.out.println(ex);
        }
    }

    private static void greetings() {
        System.out.println("Hello! I am Jeong's Slave");
        System.out.println("What can I do for you?");
    }
}
