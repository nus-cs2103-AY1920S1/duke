import java.util.Scanner;

public class Duke {
    static String[] tasks = new String[100];

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);

        int counter = 0;

        greeting();

        while(sc.hasNextLine()) {
            String command = sc.nextLine();

            switch(command.toLowerCase()) {
                case "list":
                    printList(counter);
                    break;

                case "bye":
                    handleExit();
                    return;
                
                default:
                    addTask(counter, command.toLowerCase());
                    counter ++;
                    break;
            }
        }

        sc.close();
    }

    public static void addBorder(String input) {

        String border = "____________________________________________________________";

        System.out.println(border + "\n\n" + input + "\n" + border + "\n");
    }

    public static void greeting() {
        addBorder("Hello! I'm Duke\n" + "What can I do for you?");
    }

    public static void addTask(int index, String input) {
        tasks[index] = input;
        String str = "added: " + input;
        addBorder(str);
    }

    public static void handleExit() {
        addBorder("Bye. Hope to see you again soon!");
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
