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
        Task[] taskList = new Task[100];
        int numCommands = 0;

        while (!command.equals("bye")){
            if (command.equals("list")){
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= numCommands; i++){
                    System.out.println(i + "."+ taskList[i-1]);
                }
            }
            else if (command.length() >= 4 && command.substring(0,4).equals("done")){
                int taskNumber = Integer.parseInt(command.substring(5).split(" ")[0]);
                if (taskNumber > 0 && taskNumber <= numCommands) {
                    taskList[taskNumber-1].markAsDone();
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println(taskList[taskNumber-1]);
                }
            }
            else {
                numCommands += 1;
                taskList[numCommands-1] = new Task(command);
                System.out.println("added: " + command);
            }
            command = scanner.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
