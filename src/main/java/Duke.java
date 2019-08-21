import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        String[] taskList = new String[100];
        int numCommands = 0;

        while (!command.equals("bye")){
            if (command.equals("list")){
                for (int i = 1; i <= numCommands; i++){
                    System.out.println(i + ". "+ taskList[i-1]);
                }
            }
            else {
                numCommands += 1;
                taskList[numCommands-1] = command;
                System.out.println("added: " + command);
            }
            command = scanner.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
