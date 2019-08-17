import java.util.Scanner;
import java.util.ArrayList;


public class Duke {

    private static ArrayList<Task> arr;

    public static void main(String[] args) throws DukeException {

        arr = new ArrayList<>();

        System.out.println("Hello I'm Duke! \nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);

        String command = sc.nextLine();
        while (! command.toLowerCase().equals("bye")) {

            isCommandValid(command);
            if (command.equals("list")) {
                list(command);
            } else if (command.split(" ")[0].equals("done")) {

                int num = Integer.parseInt(command.split(" ")[1]);

                markAsDone(num);

            } else if (command.split(" ")[0].equals("todo")) {
                int spaceIndex = command.indexOf(" ");
                addToDo(command.substring(spaceIndex + 1));

            } else if (command.split(" ")[0].equals("deadline")) {

                int spaceIndex = command.indexOf(" ");
                int slashIndex = command.indexOf("/");
                addDeadline(command.substring(spaceIndex + 1, slashIndex - 1), command.substring(slashIndex + 4));
            } else if (command.split(" ")[0].equals("event")) {
                int spaceIndex = command.indexOf(" ");
                int slashIndex = command.indexOf("/");
                addEvent(command.substring(spaceIndex + 1, slashIndex - 1), command.substring(slashIndex + 4));
            } else if (command.split(" ")[0].equals("delete")) {

                deleteTask(Integer.parseInt(command.split(" ")[1]));
            }


            command = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again!");

   }

   private static void list(String command) {
       System.out.println("Here are the tasks in your list:");

       int index  = 1;
       for (Task task : arr) {
           System.out.println(String.format("%d. %s", index , task.toString()));
           index++;
       }
   }

   private static void markAsDone(int num) throws DukeException{
       if (num <= 0 || num > arr.size() ) {
           throw new DukeException("OOPS!!! Number is out of range");
       } else {
           arr.get(num - 1).done();

           printDoneTask(arr.get(num-1));
       }
   }

   private static void addToDo(String taskName) {
       ToDo newToDo = new ToDo(taskName);

       arr.add(newToDo);

       printAddedTask();
   }

   private static void printDoneTask(Task t) {
        System.out.println("Nice! I've marked this task as done");
        System.out.println(String.format("    %s", t));
   }

   private static void printAddedTask() {
        System.out.println("Got it. I've added this task: ");
        System.out.println(String.format("    %s", arr.get(arr.size()-1).toString()));
        System.out.println(String.format("Now you have %d tasks in the list.", arr.size()));
   }

   private static void printDeletedTask(Task t) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println(String.format("    %s",t));
        System.out.println(String.format("Now you have %d tasks in the list.", arr.size()));
   }

   private static void addDeadline(String taskName, String datetime) {
        Deadline deadline = new Deadline(taskName,datetime);

        arr.add(deadline);

        printAddedTask();
   }

   private static void addEvent(String taskName, String datetime) {
        Event e = new Event(taskName, datetime);

        arr.add(e);

        printAddedTask();
   }

   private static void deleteTask(int index) {

        Task t = arr.remove(index);

        printDeletedTask(t);
   }

   private static boolean isCommandValid(String str) throws DukeException{


        if (! (str.split(" ")[0].equals("list") ||
            str.split(" ")[0].equals("todo") ||
            str.split(" ")[0].equals("deadline") ||
            str.split(" ")[0].equals("event") ||
            str.split(" ")[0].equals("delete") ||
            str.split(" ")[0].equals("done")
        )
        ) {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        } else if (! str.split(" ")[0].equals("list") && str.split(" ").length == 1) {
            throw new DukeException(String.format("OOPS!!! The description of a %s cannot be empty.", str.split(" ")[0]));

        } else if (str.split(" ")[0].equals("deadline") && ! str.contains("/by")) {
            throw new DukeException("OOPS!!! The description of a deadline has to be followed by '/by'.");

        } else if (str.split(" ")[0].equals("event") && ! str.contains("/at")) {
            throw new DukeException("OOPS!!! The description of an event has to be followed by '/at'.");

        } else if (str.split(" ")[0].equals("deadline") && str.split(" ")[1].equals("/by")) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");

        } else if (str.split(" ")[0].equals("event") && str.split(" ")[1].equals("/at")) {
            throw new DukeException("OOPS!!! The description of an event cannot be empty.");

        } else if (str.split(" ")[0].equals("delete") &&  ! isNumeric(str.split(" ")[1])) {
            throw new DukeException("OOPS!!! The index of the array has to be specified.");
        } else if (str.split(" ")[0].equals("delete") && isNumeric(str.split(" ")[1])
                && (Integer.parseInt(str.split(" ")[1]) < 0 ||
                Integer.parseInt(str.split(" ")[1]) >= arr.size())) {
            throw new DukeException("OOPS!!! Index out of bounds. It is larger or smaller than size of list.");
       } else if (str.split(" ")[0].equals("delete") && str.split(" ").length > 2) {
            throw new DukeException("OOPS!!! Please key in 'delete x', where x is the index that you want to delete!");
       }

        return true;
   }

    private static boolean isNumeric(String strNum) {
        return strNum.matches("-?\\d+(\\.\\d+)?");
    }
}
