import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        System.out.println("Hello from\n" + logo + "\n What can I do for you? \n" );

        while (sc.hasNext()) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (userInput.equals("list")){
                for (int i = 0; i < list.size(); i++) {
                    System.out.print((i+1) + ". " + list.get(i) + "\n");
                }
            } else {
                list.add(userInput);
                System.out.println("added: " + userInput + "\n");
            }
        }
    }
}
