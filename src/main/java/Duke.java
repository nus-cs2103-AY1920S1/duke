import java.util.LinkedList;
import java.util.Scanner;

public class Duke {
    public static void printList(LinkedList<String> taskList) {
        int counter = 1;

        for (String subList: taskList) {
            System.out.println(counter + ". " + subList);
            counter++;
        }
    }

    public static void main(String[] args) {
        LinkedList<String> taskList = new LinkedList<>();

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        String inputText = sc.nextLine();

        while (!inputText.equals("bye")) {
            if (inputText.equals("list")) {
                printList(taskList);
            } else {
                System.out.println("added: " + inputText);
                taskList.add(inputText);
            }

            inputText = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}


