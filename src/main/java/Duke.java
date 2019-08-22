import java.util.Scanner;

public class Duke {

    private static Task[] tasks = new Task[100];

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
            String inputLine = sc.nextLine();
            String[] inputArr = inputLine.split(" ");
            String input = inputArr[0];

            switch(input) {
                case "list":
                    printList(count);
                    break;
                case "bye":
                    printExitMessage();
                    return;
                case "done":
                    int completedNum = Integer.parseInt(inputArr[1]) - 1;
                    printDoneMessage(completedNum);
                    break;
                default:
                    addTask(count, inputLine.toLowerCase());
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


    public static void printDoneMessage(int completedNum) {
        Task completedTask = tasks[completedNum];
        completedTask.taskComplete();
        addBorder("Nice! I've marked this task as done: \n" + completedTask.toString());
    }

    public static void printExitMessage() {
        addBorder("Bye. Hope to see you again soon!");
    }

    public static void addTask(int index, String input) {
        tasks[index] = new Task(input);
        String output = "added: " + input;
        addBorder(output);
    }

    public static void printList(int index) {
        String str = "";

        for (int i = 1; i < index + 1; i++) {
            String newTask = tasks[i-1].toString();
            if (i == index) {
                str += i + ". " + newTask;
            } else {
                str += i + ". " + newTask + "\n";
            }
        }

        addBorder(str);
    }

}