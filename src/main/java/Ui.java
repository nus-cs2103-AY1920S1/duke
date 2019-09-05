import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private Scanner sc = new Scanner(System.in);
    protected static ArrayList<Task> taskList = new ArrayList<>();

    String line = "    ____________________________________________________________\n";

    public void greet() { //duke greet
        System.out.println(line +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" + line);
    }

    public void farewell() {
        System.out.println(line + "     Bye. Hope to see you again soon!\n" + line);
    }

    public void printFindTasks(ArrayList<Task> relatedTask) {
        System.out.println(line + "     Here are the matching tasks in your list:");
        for (int i = 0; i < relatedTask.size(); i++) {
            int num = i + 1;
            System.out.println("     " + num + "." + relatedTask.get(i));
        }
        System.out.print("\n" + line);
    }

}
