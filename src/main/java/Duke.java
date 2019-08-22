import java.util.Scanner;

public class Duke {

    private static Task[] list = new Task[100];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        String command = sc.next();
        int counter = 0;
        while (!command.equals("bye")) {

            switch(command)
            {
                case "list":
                    showList(counter);
                    break;
                case "done":
                    int indexToMark = sc.nextInt();
                    list[indexToMark - 1].markAsDone();
                    System.out.println("Nice! I've marked this task as done: \n  " +
                            list[indexToMark - 1]);
                    break;
                default:
                    addTask(sc, counter, command);
                    counter++;
            }
            command = sc.next();
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
    public static void addTask(Scanner sc, int counter, String command) {
        Task newTask;
        switch(command)
        {
            case "todo":
                newTask = new Todo(sc.nextLine());
                break;
            case "deadline":
                String details = sc.nextLine();
                String[] part = details.split("/");
                String[] time = part[1].split("by");
                newTask = new Deadline(part[0], time[1]);
                break;
            case "event":
                String details2 = sc.nextLine();
                String[] part2 = details2.split("/");
                String[] time2 = part2[1].split("at");
                newTask = new Event(part2[0], time2[1]);
                break;
            default:
                newTask = new Task(command);
        }

        list[counter] = newTask;
        System.out.println("Got it. I've added this task: \n  " +
                newTask + "\nNow you have " + (counter + 1) + " tasks in the list.");
    }
}
