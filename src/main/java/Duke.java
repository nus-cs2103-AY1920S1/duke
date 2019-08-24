import java.io.FileNotFoundException;
import java.util.Scanner;

public class Duke {

    private static String LIST_PATH = "C:/Users/Yu Han Jeong/Desktop/CS2103T/duke/src/data/duke.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        storage = new Storage(LIST_PATH);
        ui = new Ui();
        try {
            tasks = new TaskList(storage.load());
        }
        catch (FileNotFoundException ex) {
            System.out.println("create Duke exception and handle this");
        }
    }

    public void run() {
        ui.greetings();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            Command c = Parser.parse(fullCommand);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();

//            } else if (command.equals("done")) {
//                int itemIndex = sc.nextInt();
//                tasks.markIndexedTaskAsDone(itemIndex);
//                storage.writeListToFile(tasks);
//            } else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) { // new item
//                Task task = addNewItem(command, sc, tasks);
//                storage.addTaskToFile(task);
//            } else if (command.equals("delete")) {
//                int itemIndex = sc.nextInt();
//                tasks.deleteTask(itemIndex);
//                storage.writeListToFile(tasks);
//            } else {
//                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
//                sc.nextLine();
//            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    private static Task addNewItem(String command, Scanner sc, TaskList taskList) {
        String item = sc.nextLine().trim();
        if (item.equals("")) {
            System.out.printf("☹ OOPS!!! The description of a %s cannot be empty.\n", command);
            return null;
        }
        Task task;
        if (command.equals("todo")) {
            task = new ToDo(item);
        } else if (command.equals("deadline")) {
            try {
                String description = item.split("/")[0].trim();
                String deadline = item.substring(item.indexOf("by") + 3).trim();
                task = new Deadline(description, deadline);
            }
            catch (ArrayIndexOutOfBoundsException ex) {
                System.out.println("Invalid deadline input");
                return null;
            }
        } else if (command.equals("event")) {
            try {
                String[] tasksSlashTiming = item.split("/");
                String timing = tasksSlashTiming[1].substring(3);
                task = new Event(tasksSlashTiming[0].trim(), timing);
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.out.println("Invalid event input");
                return null;
            }
        } else {
            System.out.println("Task type can only be of type todo, deadline or event");
            return null;
        }
        taskList.addItem(task);
        return task;
    }

}