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

}
