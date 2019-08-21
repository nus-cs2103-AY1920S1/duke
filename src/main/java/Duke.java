import java.util.Scanner;

public class Duke {
    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Hello! I'm \n" + logo + "What can I do for you?");

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            String[] inputArr = input.split("\\s+", 2);
            String command = inputArr[0];
            switch (command) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    return;
                case "list":
                    Task.printList();
                    break;
                case "done":
                    if (inputArr.length > 1) {
                        Task.doTask(Integer.parseInt(inputArr[1]));
                    }
                    break;
                default:
                    new Task(input);
                    break;
            }
        }

        sc.close();
    }
}
