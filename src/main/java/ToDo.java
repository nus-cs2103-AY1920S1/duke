import java.util.Scanner;
import java.util.ArrayList;

class ToDo {
    private ArrayList<String> todoList = new ArrayList<>();

    ToDo() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    void run() {
        Scanner sc = new Scanner(System.in);
        String nextCommand = sc.nextLine();
        while (!nextCommand.equals("bye")) {
            if (nextCommand.equals("list")) {
                for (int i = 0; i < todoList.size(); i++) {
                    System.out.println(i + 1 + ". " + todoList.get(i));
                }
                nextCommand = sc.nextLine();
            } else {
                todoList.add(nextCommand);
                System.out.println("added: " + nextCommand);
                nextCommand = sc.nextLine();
            }
        }
        System.out.println("Bye! Hope to see you again soon!");
    }
}
