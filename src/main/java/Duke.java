import java.util.Scanner;
import java.util.ArrayList;


public class Duke {
    public static void main(String[] args) {

        ArrayList<Task> arr = new ArrayList<>();

        System.out.println("Hello I'm Duke! \nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);

        String command = sc.nextLine();
        while (! command.toLowerCase().equals("bye")) {

            if (command.equals("list")) {
                System.out.println("Here are the tasks in your list:");

                int index  = 1;
                for (Task task : arr) {
                    System.out.println(String.format("%d. %s", index , task.toString()));
                    index++;
                }
            } else if (command.split(" ")[0].equals("done")) {

                int num = Integer.parseInt(command.split(" ")[1]);

                if (num <= 0 || num > arr.size() ) {
                    System.out.println("Number is out of range");
                } else {
                    arr.get(num - 1).done();
                }

            } else {
                arr.add(new Task(command));
                System.out.println(String.format("added: %s", command));
            }

            command = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again!");

   }
}
