import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



public class Duke {

    private static ArrayList<Task> arr;
    private static Scanner sc;

    public static void main(String[] args) throws DukeException, FileNotFoundException, IOException {


        arr = new ArrayList<>();

        readFile();

        readCommand();

    }

    private static void readCommand() throws DukeException , IOException{
        System.out.println("Hello I'm Duke! \nWhat can I do for you?");
       sc = new Scanner(System.in);


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
               printAddedTask();
           } else if (command.split(" ")[0].equals("deadline")) {

               int spaceIndex = command.indexOf(" ");
               int slashIndex = command.indexOf("/");
               addDeadline(command.substring(spaceIndex + 1, slashIndex - 1), command.substring(slashIndex + 4));
               printAddedTask();

           } else if (command.split(" ")[0].equals("event")) {
               int spaceIndex = command.indexOf(" ");
               int slashIndex = command.indexOf("/");
               addEvent(command.substring(spaceIndex + 1, slashIndex - 1), command.substring(slashIndex + 4));

               printAddedTask();
           } else if (command.split(" ")[0].equals("delete")) {

               deleteTask(Integer.parseInt(command.split(" ")[1]));
           }

           writeListToFile();


           command = sc.nextLine();
       }

       System.out.println("Bye. Hope to see you again!");

    }

    private static void readFile() throws FileNotFoundException {
        File f = new File("data/duke.txt");
        sc = new Scanner (f);
        while (sc.hasNext()) {
            String line = sc.nextLine();

            String[] stringArr = line.split("\\|");


            if (stringArr[0].trim().equals("T")) {

                addToDo(stringArr[2].trim());
            } else if (stringArr[0].trim().equals("D")) {
                addDeadline(stringArr[2].trim(), stringArr[3].trim());
            } else if (stringArr[0].trim().equals("E")) {
                addEvent(stringArr[2].trim(), stringArr[3].trim());
            }

            if (stringArr[1].trim().equals("1")) {
                arr.get(arr.size()-1).markAsDone();
            }

        }


    }

    private static void writeListToFile() throws IOException {
        FileWriter fw = new FileWriter("data/duke.txt");
        StringBuilder sb = new StringBuilder();
        for (Task entry : arr) {
            if (entry instanceof Deadline) {
                sb.append(String.format("D | %s | %s | %s", entry.isDone() ? "1" : "0",
                        entry.getTaskName(), ((Deadline) entry).getDateTime() ));
            } else if (entry instanceof Event) {
                sb.append(String.format("E | %s | %s | %s", entry.isDone() ? "1" : "0",
                        entry.getTaskName(), ((Event) entry).getDateTime() ));
            } else if (entry instanceof ToDo) {
                sb.append(String.format("T | %s | %s", entry.isDone() ? "1" : "0",
                        entry.getTaskName() ));
            }

            sb.append(System.lineSeparator());
            fw.write(sb.toString());
            sb = new StringBuilder();
        }

        fw.close();

    }

    private static void list(String command) {
       System.out.println("Here are the tasks in your list:");

       int index  = 1;
       for (Task task : arr) {
           System.out.println(String.format("%d. %s", index , task.toString()));
           index++;
       }
    }

    private static void markAsDone(int num) throws DukeException {
       if (num <= 0 || num > arr.size() ) {
           throw new DukeException("OOPS!!! Number is out of range");
       } else {
           arr.get(num - 1).markAsDone();

           printDoneTask(arr.get(num-1));
       }
    }

    private static void addToDo(String taskName) {
       ToDo newToDo = new ToDo(taskName);

       arr.add(newToDo);

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

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM yyyy HHmm");
        LocalDateTime dateTimeObj =  LocalDateTime.parse(datetime, dtf);
        Deadline deadline = new Deadline(taskName, dateTimeObj);

        arr.add(deadline);


   }

   private static void addEvent(String taskName, String datetime) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM yyyy HHmm");
        LocalDateTime dateTimeObj =  LocalDateTime.parse(datetime, dtf);

        Event e = new Event(taskName, dateTimeObj);

        arr.add(e);


   }

    private static void deleteTask(int index) {

        Task t = arr.remove(index-1);

        printDeletedTask(t);
    }

    private static boolean isCommandValid(String str) throws DukeException {


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
                && (Integer.parseInt(str.split(" ")[1]) <= 0 ||
                Integer.parseInt(str.split(" ")[1]) > arr.size())) {
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
