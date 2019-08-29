import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class Ui {

    private Scanner sc;
    private TaskList taskList;
    private Storage storage;

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

    public void scan(TaskList taskList, Storage storage) throws IOException {
        this.taskList = taskList;
        this.storage = storage;
        String input = sc.nextLine();
        String[] task = input.split(" ");
        while (!task[0].equals("bye")) {
            try {
                handleInput(task, input);
            } catch (IllegalArgumentException e) {
                print("    ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (IndexOutOfBoundsException e) {
                print("    ☹ OOPS!!! I'm sorry, but task number " + task[1] + " does not exist.");
            } finally {
                storage.saveTasks(taskList);
                input = sc.nextLine();
                task = input.split(" ");
            }
        }
        System.out.println("    Bye. Hope to see you again soon!");
    }

    private void handleInput(String[] task, String input) throws IllegalArgumentException, IndexOutOfBoundsException {
        switch (task[0]) {
        case "delete":
            Task deletedTask = taskList.delete(Integer.parseInt(task[1]));
            print("    Noted. I've removed this task:");
            System.out.println("        " + deletedTask);
            print("    Now you have " + taskList.getListSize() + " tasks in the list.");
            break;
        case "done":
            Task doneTask = taskList.markAsDone(Integer.parseInt(task[1]));
            print("    Nice! I've marked this task as done:");
            System.out.println("    " + doneTask);
            break;
        case "list":
            print("    Here are the tasks in your list:");
            taskList.printList();
            break;
        case "find":
            print("    Here are the matching tasks in your list:");
            taskList.find(task[1]);
            break;
        case "todo":
        case "deadline":
        case "event":
            handleTasks(task[0], input);
            break;
        default:
            throw new IllegalArgumentException();
        }
    }

    private void handleTasks(String taskType, String t) {
        Parser p = new Parser(t);
        try {
            Task parsedTask = p.parse();
            taskList.addToList(parsedTask);
            System.out.println("    Got it. I've added this task:");
            System.out.println("        " + parsedTask);
            System.out.println("    Now you have " + taskList.getListSize() + " tasks in the list.");
        } catch (DukeException e) {
            print("    ☹ OOPS!!! The description of a " + taskType + " cannot be empty."); // check in parser
        } catch (ParseException e) {
            print("    ☹ OOPS!!! The date/time of a " + taskType + " does not follow the specified format of dd/MM/yyyy HHmm.");
        } catch (ArrayIndexOutOfBoundsException e) {
            print("    ☹ OOPS!!! The description of a " + taskType + " does not follow the specified format.");
        }
    }

    private void print(String s) {
        System.out.println(s);
    }
}
