import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greet = "Hello! I'm Duke\nWhat can I do for you?\n";
        System.out.println(greet);
        
        Scanner scanner = new Scanner(System.in);
        
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } 
            System.out.println(input);
            System.out.println();
        }
    }
}
