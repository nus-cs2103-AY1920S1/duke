import java.util.Scanner;

public class Ui {
    private static TaskList taskList;
    private static Storage storage;
    private static Parser parser;

    public Ui() {
    }

    public void start(Parser parser, Storage storage, TaskList taskList) {
        this.parser = parser;
        this.storage = storage;
        this.taskList = taskList;

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        String word = sc.nextLine();

        while (!word.equals("bye")) {
            try {
                parser.parseLine(word);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Must input an integer");
            } finally {
                word = sc.nextLine();
            }

        }
        storage.updateFile(taskList.getList());
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showLoadingError() {
        System.out.println("FIle not founddd");
    }

    public void printList() {
        int i = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task task : taskList.getList()) {
            System.out.println(i + ". " + task);
            i++;
        }
    }

    public void printDelete(Task task) {
        System.out.println("Noted. I've removed this task: : ");
        System.out.println("\t" + task);
        System.out.println("Now you have " +  taskList.getSize()  +  " tasks in the list.");
    }

    public void printDone(Task task) {
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("\t" + task);
    }

    public void printAdd(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + task.toString());
        System.out.println("Now you have " +  taskList.getSize()  +  " tasks in the list.");
    }
}
