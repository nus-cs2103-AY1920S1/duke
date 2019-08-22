import java.util.Scanner;
import java.util.ArrayList;

class ToDo {
    private ArrayList<Task> todoList = new ArrayList<>();

    ToDo() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    void run() {
        Scanner sc = new Scanner(System.in);
        String nextCommand = sc.nextLine();
        while (!nextCommand.equals("bye")) {
            String[] splitCommand = nextCommand.split(" ");
            if (splitCommand.length == 1 && splitCommand[0].equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < todoList.size(); i++) {
                    System.out.println(i + 1 + "." + todoList.get(i));
                }
                nextCommand = sc.nextLine();
            } else if (splitCommand[0].equals("done")) {
                int index = Integer.parseInt(splitCommand[1]) - 1;
                todoList.get(index).markAsDone();
                nextCommand = sc.nextLine();
            } else {
                Task newTask = new Task(nextCommand);
                todoList.add(newTask);
                System.out.println("added: " + nextCommand);
                nextCommand = sc.nextLine();
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
