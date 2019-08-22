import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> inputList = new ArrayList<>();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("___________________________________");
        System.out.println("Hello! I'm Duke\nWhat can i do for you?");
        System.out.println("___________________________________");

        while(sc.hasNext()) {
            String inputString = sc.nextLine();
            if (inputString.equals("bye")) {
                System.out.println("___________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("___________________________________");
                break;
            } else if (inputString.equals("list")) {
                System.out.println("___________________________________");
                for (int i = 0; i < inputList.size(); i++) {
                    System.out.println(i+1 + ". " + inputList.get(i));
                }
                System.out.println("___________________________________");
            } else {
                System.out.println("___________________________________");
                System.out.println("added: " + inputString);
                System.out.println("___________________________________");
                inputList.add(inputString);
            }
        }
    }
}
