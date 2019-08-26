import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String f = "data/duke.txt";
        String logo = "     ____        _        \n"
                + "    |  _ \\ _   _| | _____ \n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|\n";
        System.out.println("    Hello from\n" + logo + "    Hello! I'm Duke\n    What can I do for you?");
        String input  = sc.nextLine();
        String[] wordArray = input.split(" ");
        TaskList list = new TaskList();
        try {
            list.loadTasks(f);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        while (!wordArray[0].equals("bye")) {
            try {
                switch (wordArray[0]) {
                case "list":
                    list.printList();
                    break;
                case "done":
                    list.markAsDone(Integer.parseInt(wordArray[1]));
                    break;
                case "todo":
                case "event":
                case "deadline":
                    list.addToList(input);
                    break;
                case "delete":
                    list.delete(Integer.parseInt(wordArray[1]));
                    break;
                default:
                    throw new DukeException();
                }
                list.saveTasks(f);
            } catch (DukeException e) {
                System.out.println("    ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage()); // or saved file is missing?
            } finally {
                input = sc.nextLine();
                wordArray = input.split(" ");
            }
        }
        System.out.println("    Bye. Hope to see you again soon!");
    }
}
