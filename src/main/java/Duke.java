import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        greet();
        Task[] inputArr = new Task[100];
        Scanner sc = new Scanner(System.in);
        int i = 0;
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if(input.equals("list")) {
                listTasks(inputArr);
            } else if(input.contains("done")) {
                String[] splitInputs = input.split(" ");
                int index = Integer.valueOf(splitInputs[1]) - 1;
                inputArr[index].markAsDone();
            }    else if (!input.equals("bye")) {
                echo(input);
                Task t = new Task(input);
                inputArr[i] = t;
                i++;
            } else {
                exit();
                break;
            }
        }
    }

    public static void greet() {
        String message = "Hello! I'm Duke\n" +
                "What can I do for you?";
        System.out.println(message);
    }

    public static void echo(String s) {
        System.out.println("added: " + s);
    }

    public static void exit() {
        String byeMessage = "Bye. Hope to see you again soon!";
        System.out.println(byeMessage);
    }

    public static void listTasks(Task[] arr) {
        System.out.println("Here are the tasks in your list:");
        for (int j = 0; (j < arr.length) && arr[j] != null; j++) {
            System.out.println(j + 1 + "." + arr[j].getStatusIcon() + " " + arr[j].getDescription());
        }
    }
}
