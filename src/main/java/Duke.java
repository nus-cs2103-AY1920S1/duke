import java.util.Scanner;

public class Duke {
    static String[] tasks = new String[100];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count = 0;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        printGreetingMessage();

        while(sc.hasNextLine()) {
            String input = sc.nextLine();

            switch(input) {
                case "list":
                    printList(count);
                    break;
                case "bye":
                    printExitMessage();
                    return;

                default:
                    printDefaultMessage(input.toLowerCase());
                    addTask(count, input.toLowerCase());
                    count ++;
                    break;
            }
        }
    }


    public static void addBorder(String input) {

        String border = "__________________________________________________________";

        System.out.print(border + "\n\n" + input + "\n" + border + "\n\n");
    }


    public static void printGreetingMessage() {
        addBorder("Hello! I'm Duke\n" + "What can I do for you?");
    }


    public static void printDefaultMessage(String input) {
        addBorder(input);
    }

    public static void printExitMessage() {
        addBorder("Bye. Hope to see you again soon!");
    }

    public static void addTask(int index, String input) {
        tasks[index] = input;
        String output = "added: " + input;
        addBorder(output);
    }

    public static void printList(int index) {
        String str = "";

        for (int i = 1; i < index + 1; i++) {
            if (i == index) {
                str += i + ". " + tasks[i-1];
            } else {
                str += i + ". " + tasks[i-1] + "\n";
            }
        }

        addBorder(str);
    }

}