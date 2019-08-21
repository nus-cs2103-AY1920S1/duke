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
            String[] keyList = inputText.split(" ", 2);
            String actionKey = keyList[0];

            if (inputText.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                int counter = 1;

                for (Task subTask: taskList) {
                    System.out.println(counter + ". " + subTask);
                    counter++;
                }
            } else if (actionKey.equals("done")) {
                System.out.println("Nice! I've marked this task as done: ");

                int index = Integer.parseInt(inputText.split(" ")[1]);
                Task selectedTask = taskList.get(index - 1);
                selectedTask.markAsDone();

                System.out.println("[" + selectedTask.getStatusIcon() + "] " + selectedTask.getDescription());
            } else {
                String descriptionKey = "";

                try {
                    descriptionKey = keyList[1];

                    System.out.println("Got it. I've added this task: ");
                    if (actionKey.equals("deadline")) {
                        String[] contentList = descriptionKey.split(" /by ");
                        Deadline newDeadline = new Deadline(contentList[0], contentList[1]);
                        System.out.println(newDeadline);
                        taskList.add(newDeadline);
                    } else if (actionKey.equals("event")) {
                        String[] contentList = descriptionKey.split(" /at ");
                        Event newEvent = new Event(contentList[0], contentList[1]);
                        System.out.println(newEvent);
                        taskList.add(newEvent);
                    } else if (actionKey.equals("todo")) {
                        Todo newTodo = new Todo(inputText.split(" ", 2)[1]);
                        System.out.println(newTodo);
                        taskList.add(newTodo);
                    } else {
                        Task newTask = new Task(inputText.split(" ", 2)[1]);
                        System.out.println(newTask);
                        taskList.add(newTask);
                    }
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                } catch (ArrayIndexOutOfBoundsException ex) {
                    // command error
                }
            }

            inputText = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}


