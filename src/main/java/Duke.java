import java.util.Scanner;

public class Duke {

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

    //The main method will be used to recognize inputs and call relative methods
    public static void main(String[] args) {
        draw_line();
        System.out.println("     Hello, I'm Duke.");
        System.out.println("     What can I do for you?");
        draw_line();
        while(true){
            Scanner sc = new Scanner(System.in);
            String input = sc.next();
            if (input.equals("bye")) break;
            repeat(input);
        }
        draw_line();
        System.out.println("     Bye. Hope to see you again soon!");
        draw_line();
    }
}
