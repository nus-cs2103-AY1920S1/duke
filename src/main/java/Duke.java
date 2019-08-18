import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static ArrayList<String> tasklist = new ArrayList<>();

    private static void draw_line() {
        System.out.print("    ");
        for (int i = 0; i < 70; i++) System.out.print("_");
        System.out.print("\n");
    }

    private static void repeat(String message) {
        draw_line();
        System.out.println("     " + message);
        draw_line();
    }

    private static void add_task(String task_name) {
        tasklist.add(task_name);
        draw_line();
        System.out.println("     " + "added: " + task_name);
        draw_line();
    }

    private static void list_task() {
        draw_line();
        for (int i = 0; i < tasklist.size(); i++) {
            System.out.println("     " + (i + 1) + ". " + tasklist.get(i));
        }
        draw_line();
    }

    //The main method will be used to recognize inputs and call relative methods
    public static void main(String[] args) {
        draw_line();
        System.out.println("     Hello, I'm Duke.");
        System.out.println("     What can I do for you?");
        draw_line();
        while(true){
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            if (input.equals("bye")) break;
            if (input.equals("list")) list_task();
            else add_task(input);
        }
        draw_line();
        System.out.println("     Bye. Hope to see you again soon!");
        draw_line();
    }
}
