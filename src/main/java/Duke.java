import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> commands = new ArrayList<String>();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        while(scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                scanner.close();
                break;
            } else if(input.equals("list")) {
                int count = 1;
                for (String s : commands) {
                    System.out.println(count + ". " + s);
                    count++;
                }
            } else {
                commands.add(input);
                System.out.println("added: " + input);
            }
        }
    }
}
