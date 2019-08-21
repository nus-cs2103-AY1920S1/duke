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
        System.out.println("\n");
        System.out.println("How may I help you?\n");
        System.out.println("--------------------------------------\n");
        String[] tasks = new String[100];
        int numberOfTask = 0;
        boolean shouldStop = false;
        while (!shouldStop) {
            String input = sc.nextLine();
            System.out.println("\n--------------------------------------");
            switch (input) {
                case "bye":
                    System.out.println("    Bye! See you again soon!!\n");
                    System.out.println("--------------------------------------");
                    shouldStop = true;
                    break;
                case "list":
                        for (int i = 0; i < numberOfTask; i++) {
                            System.out.println("    " + (i + 1) + ". " + tasks[i]);
                        }
                    System.out.println("--------------------------------------");
                    break;
                default:
                    System.out.println("    added: " + input + "\n");
                    tasks[numberOfTask] = input;
                    numberOfTask++;
                    System.out.println("--------------------------------------");
                    break;
            }
        }
    }
}
