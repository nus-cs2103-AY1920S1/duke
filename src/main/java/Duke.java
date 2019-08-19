import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);

        greeting();

        while(sc.hasNextLine()) {
            String command = sc.nextLine();

            switch(command.toLowerCase()) {
                case "bye":
                    handleExit();
                    return;
                
                default:
                    handleDefault(command.toLowerCase());
                    break;
            }
        }
    }

    public static void addBorder(String input) {

        String border = "____________________________________________________________";

        System.out.println(border + "\n\n" + input + "\n" + border + "\n");
    }

    public static void greeting() {
        addBorder("Hello! I'm Duke\n" + "What can I do for you?");
    }

    public static void handleDefault(String input) {
        addBorder(input);
    }

    public static void handleExit() {
        addBorder("Bye. Hope to see you again soon!");
    }

}
