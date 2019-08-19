import java.util.Scanner;

public class ToDoList {
    public void run() {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();
        while (!input.equals("bye")){
            System.out.println(input);
            input = sc.nextLine();
        }
        if (input.equals("bye")){
            System.out.println("Bye. Hope to see you again soon!");
        }
    }
}
