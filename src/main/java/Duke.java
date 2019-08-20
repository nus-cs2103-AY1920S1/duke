import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static ArrayList<String> list = new ArrayList<>();

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

            if (input.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i + 1) + ". " + list.get(i));
                }
            } else {
                list.add(input);
                System.out.println("added: " + input);
            }
            System.out.println();
        }
    }
}
