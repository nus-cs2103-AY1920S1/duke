import java.util.Scanner;

public class Duke {

    private static Task[] list = new Task[100];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        String command = sc.nextLine();
        int counter = 0;
        while (!command.equals("bye")) {
            if (command.equals("list")) {
                showList(counter);
            }
            else {
                String[] part = command.split(" ");
                if (part[0].equals("done")) {
                    int indexToMark = Integer.parseInt(part[1]);
                    list[indexToMark - 1].markAsDone();
                } else {
                    addTask(counter, command);
                    counter++;
                }
            }
            command = sc.nextLine();
        }
        if (command.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            sc.close(); //exit the program
        }
    }

    // To print out the items stored in the list.
    public static void showList(int counter) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < counter; i++) {
            System.out.println((i+1) + ". " + list[i]);
        }
    }

    // To add a new task into the list array.
    public static void addTask(int counter, String command) {
        Task newTask = new Task(command);
        list[counter] = newTask;
        System.out.println("added: " + command);
    }
}
