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
                int taskNumber = Character.getNumericValue(command.charAt(command.length()-1));
                if (taskNumber > 0 && taskNumber <= numCommands) {
                    taskList[taskNumber-1].markAsDone();
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println(taskList[taskNumber-1]);
                }
            }
            else if (command.length() >= 4 && command.substring(0,4).equals("todo")){
                numCommands += 1;
                taskList[numCommands-1] = new Todo(command.substring(5));
                System.out.println("Got it. I've added this task: ");
                System.out.println(taskList[numCommands-1]);
                System.out.println("Now you have "  + numCommands + " tasks in the list.");
            }
            else if (command.length() >= 8 && command.substring(0,8).equals("deadline")){
                numCommands += 1;
                String[] commandLine = command.substring(9).split(" /by ");
                taskList[numCommands-1] = new Deadline(commandLine[0], commandLine[1]);
                System.out.println("Got it. I've added this task: ");
                System.out.println(taskList[numCommands-1]);
                System.out.println("Now you have "  + numCommands + " tasks in the list.");
            }
            else if (command.length() >= 5 && command.substring(0,5).equals("event")){
                numCommands += 1;
                String[] commandLine = command.substring(6).split(" /at ");
                taskList[numCommands-1] = new Event(commandLine[0], commandLine[1]);
                System.out.println("Got it. I've added this task: ");
                System.out.println(taskList[numCommands-1]);
                System.out.println("Now you have "  + numCommands + " tasks in the list.");
            }
            command = scanner.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
