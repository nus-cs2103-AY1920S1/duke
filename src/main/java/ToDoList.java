import java.util.Scanner;

public class ToDoList {
    public void run() {
        Scanner sc = new Scanner(System.in);
        String border = "    ____________________________________________________________";

        String input = sc.nextLine();
        while (!input.equals("bye")){
            System.out.println(border + "\n" + "     " + input + "\n" + border);
            input = sc.nextLine();
        }
        if (input.equals("bye")){
            System.out.println(border + "\n" + "     Bye. Hope to see you again soon!" + "\n" +border);
        }
    }
}
