import java.util.LinkedList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        LinkedList<Task> taskList = new LinkedList<>();

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        String inputText = sc.nextLine();

        while (!inputText.equals("bye")) {
            if (inputText.equals("list")) {
                int counter = 1;

                for (Task subTask: taskList) {
                    System.out.println(counter + ".[" + subTask.getStatusIcon() + "] " + subTask.getDescription());
                    counter++;
                }
            } else if (inputText.split(" ")[0].equals("done")) {
                System.out.println("Nice! I've marked this task as done: ");

                int index = Integer.parseInt(inputText.split(" ")[1]);
                Task selectedTask = taskList.get(index - 1);
                selectedTask.markAsDone();

                System.out.println("[" + selectedTask.getStatusIcon() + "] " + selectedTask.getDescription());
            } else {
                System.out.println("added: " + inputText);

                Task newTask = new Task(inputText);
                taskList.add(newTask);
            }

            inputText = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}


