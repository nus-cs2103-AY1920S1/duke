import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        //dukeSayHello();
        greetings();

        //level 4
        Scanner sc = new Scanner(System.in);
        ItemsList itemsList = new ItemsList();
        while (sc.hasNext()) {
            String command = sc.next();
            if (command.equals("list")) {
                itemsList.printList();
            }
            else if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else if (command.contains("done")) {
                String inputArr[] = command.split(" ");
                int itemIndex = Integer.valueOf(inputArr[1]);
                Task task = itemsList.getTaskAtIndex(itemIndex - 1);
                task.markAsDone();
            }
            else { // new item
                addNewItem(command, sc, itemsList);
            }
        }
    }

    private static void addNewItem(String command, Scanner sc, ItemsList itemsList) {
        String item = sc.nextLine().trim();
        Task task;
        if (command.equals("todo")) {
            itemsList.addItem(new ToDo(item));
        }
        else if (command.equals("deadline")) {
            String[] itemSlashDeadline = item.split("/");
            String deadline = itemSlashDeadline[1].substring(3);
            task = new Deadline(itemSlashDeadline[0].trim(), deadline);
            itemsList.addItem(task);
        }
        else { //command.equals("event")
            String[] itemsSlashTiming = item.split("/");
            String timing = itemsSlashTiming[1].substring(3);
            task = new Event(itemsSlashTiming[0].trim(), timing);
            itemsList.addItem(task);
        }
    }

    private static void greetings() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    private static void dukeSayHello() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }
}
