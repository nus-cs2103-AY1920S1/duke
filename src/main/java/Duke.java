import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        ArrayList<String> alist = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            if(input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!\n");
            } else if(input.equals("list")) {
                for(int i = 1; i <= alist.size(); i++) {
                    System.out.println(i + ". " + alist.get(i - 1));
                }
            } else {
                alist.add(input);
                System.out.println("added: " + input + "\n");
            }
        }
    }
}
