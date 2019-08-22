import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

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
            } else {
                System.out.println("___________________________________");
                System.out.println(inputString);
                System.out.println("___________________________________");
            }
        }
    }
}
