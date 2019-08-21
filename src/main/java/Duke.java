import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
     
        Scanner sc = new Scanner(System.in);

        // Print initial welcome string 
        System.out.println("    --------------------------------------------------------");
        System.out.println(" Hello! I'm Duke :)\n What can I do for you?");
        System.out.println("    --------------------------------------------------------");

        // Run till user types bye
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            System.out.println("    --------------------------------------------------------");
            System.out.println("     " + input);
            System.out.println("    --------------------------------------------------------");
            input = sc.nextLine();
        }

        // Print exit string
        System.out.println("    --------------------------------------------------------");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    --------------------------------------------------------");
    }
}
