import java.text.ParseException;
import java.util.Scanner;

public class Ui {

    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
        String f = "data/duke.txt";
        String logo = "     ____        _        \n"
                + "    |  _ \\ _   _| | _____ \n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|\n";
        System.out.println("    Hello from\n" + logo + "    Hello! I'm Duke\n    What can I do for you?");
    }

    public void scan(TaskList tasks, Storage storage) {
        String input = sc.nextLine();
        Parser p;
        String[] task = input.split(" ");
        while (!task[0].equals("bye")) {
            p = new Parser(input);
            try {
                task = p.parse();
                switch (task[0]) {
                case "delete":
                    tasks.delete(Integer.parseInt(task[1]));
                    break;
                case "done":
                    tasks.markAsDone(Integer.parseInt(task[1]));
                    break;
                case "list":
                    tasks.printList();
                    break;
                case "todo":
                case "deadline":
                case "event":
                    tasks.addToList(task);
                    break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("    ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (DukeException e) {
                System.out.println("    ☹ OOPS!!! The description of a " + task[0] + " cannot be empty.");
            } catch (ParseException e) {
                System.out.println("    ☹ OOPS!!! The description of a " + task[0] + " does not follow the specified format."); // CHANGE THIS
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("    ☹ OOPS!!! The description of a " + task[0] + " does not follow the specified format.");
            }
            storage.saveTasks(tasks);
            input = sc.nextLine();
            task = input.split(" ");
        }
        System.out.println("    Bye. Hope to see you again soon!");
    }

}
