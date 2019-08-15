import java.util.Scanner;
import java.util.ArrayList;


public class Duke {

    public static ArrayList<Task> arr;

    public static void main(String[] args) {

        arr = new ArrayList<>();

        System.out.println("Hello I'm Duke! \nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);

        String command = sc.nextLine();
        while (! command.toLowerCase().equals("bye")) {

            if (command.equals("list")) {
                list(command);
            } else if (command.split(" ")[0].equals("done")) {

                int num = Integer.parseInt(command.split(" ")[1]);

                markAsDone(num);

            } else if (command.split(" ")[0].equals("todo")){

                addToDo(command.split(" ")[1]);


            }

            command = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again!");

   }

   public static void list(String command) {
       System.out.println("Here are the tasks in your list:");

       int index  = 1;
       for (Task task : arr) {
           System.out.println(String.format("%d. %s", index , task.toString()));
           index++;
       }
   }

   public static void markAsDone(int num) {
       if (num <= 0 || num > arr.size() ) {
           System.out.println("Number is out of range");
       } else {
           arr.get(num - 1).done();
       }
   }

   public static void addToDo(String taskName) {
       ToDo newToDo = new ToDo(taskName);

       arr.add(newToDo);

       printAddedTask();
   }

   public static void printAddedTask() {
        System.out.println("Got it. I've added this task: ");
        System.out.println(String.format("    %s", arr.get(arr.size()-1).toString()));
        System.out.println(String.format("Now you have %d tasks in the list.", arr.size()));
   }
}
