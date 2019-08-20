import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> list = new ArrayList<>(); 
    private static int count = 0;
    public static void main(String[] args) {
        welcomeMessage();
        Scanner sc = new Scanner(System.in);
        while (true) {
          String input = sc.next();
          if (input.equals("bye")) {
            byeMessage();
            break;
          } else if (input.equals("list")) {
            displayList();
          } else if (input.equals("done")) {
            try {
              markItemComplete(Integer.parseInt(sc.next()));
            } catch (DukeException e) {
              printErrorMessage(e.getMessage());
            }
          } else {
            try {
              String type = input;
              if (
                !type.equals("todo") &&
                !type.equals("deadline") &&
                !type.equals("event")
              ) {
                throw new DukeException("I don't know what that means :(");
              }
              String taskName = sc.nextLine();
              Task t = addToList(taskName, type);
              echo(t);
            } catch (DukeException e) {
              printErrorMessage(e.getMessage());
            }
          } 
        }
        sc.close();
    }

    static void printErrorMessage(String e) {
      String line = "    ________________________" 
          + "____________________________________";
      System.out.println(line);
      System.out.println("     " + e);
      System.out.println(line + "\n");
    }

    static void displayList() {
      String line = "    ________________________" 
          + "____________________________________";
      System.out.println(line);
      System.out.println("    Here are the tasks in your list:");
      for (int i = 0; i < count; i++) {
        int listNumber = i + 1;
        System.out.println("    " + listNumber + "." + list.get(i));
      }
      System.out.println(line + "\n");
    }

    static void markItemComplete(int index) throws DukeException {
      if (index <= 0 || index > count) {
        throw new DukeException("Invalid task number!");
      }
      String line = "    ________________________" 
          + "____________________________________";
      Task t = list.get(index - 1);
      t.complete();
      System.out.println(line);
      System.out.println("     Nice! I've marked this task as done:");
      System.out.println("     " + t);
      System.out.println(line + "\n");
    }

    static Task addToList(String s, String type) throws DukeException {
      String trimmed = s.replaceAll("^\\s+", "");
      if (trimmed.equals("")) {
        throw new DukeException("Description cannot be empty!");
      }
      if (type.equals("todo")) {
        list.add(new Todo(s, count + 1));
      } else if (type.equals("deadline")) {
        String[] parts = s.split("\\/" + "by");
        if (parts.length < 2) {
          String message = "Date required! ";
          message += "Format: deadline YOUR_TASK_NAME /by YOUR_DEADLINE";
          throw new DukeException(message);
        } else if (parts.length != 2) {
          String message = "Format: deadline YOUR_TASK_NAME /by YOUR_DEADLINE";
          throw new DukeException(message);
        }
        list.add(new Deadline(parts[0], count + 1, parts[1]));
      } else if (type.equals("event")) {
        String[] parts = s.split("\\/" + "at");
        if (parts.length < 2) {
          String message = "Date required! ";
          message += "Format: event YOUR_TASK_NAME /at YOUR_EVENT_DATE";
          throw new DukeException(message);
        } else if (parts.length != 2) {
          String message = "Format: event YOUR_TASK_NAME /at YOUR_EVENT_DATE";
          throw new DukeException(message);
        }
        list.add(new Event(parts[0], count + 1, parts[1]));
      }
      count++;
      return list.get(count - 1);
    }
    static void welcomeMessage() {
        String logo = "     ____        _        \n"
                + "    |  _ \\ _   _| | _____ \n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|\n";
        String line = "    ________________________" 
            + "____________________________________";
        System.out.println(line);
        System.out.println("    Hello from\n" + logo);
        System.out.println("    What can I do for you?");
        System.out.println(line + "\n");
    }

    static void byeMessage() {
        String line = "    ________________________" 
          + "____________________________________";
        System.out.println(line);
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println(line + "\n");
    }
    
    public static void echo(Task t) {
        String line = "    ________________________" 
            + "____________________________________";
        System.out.println(line);
        System.out.println("     Got it. I've added this task:");
        System.out.println("      " + t);
        if (count == 1) {
          System.out.println("     Now you have 1 task in the list.");
        } else {
          String message = "     Now you have "+count+" tasks in the list.";
          System.out.println(message);
        }
        System.out.println(line + "\n");
        return;
    }
}
