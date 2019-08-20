import java.util.Scanner;

public class Duke {
    private static Task[] list = new Task[100];
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
            markItemComplete(Integer.parseInt(sc.next()));
          } else {
            String type = input;
            String taskName = sc.nextLine().substring(1);
            Task t = addToList(taskName, type);
            echo(t);
          } 
        }
        sc.close();
    }

    static void displayList() {
      String line = "    ________________________" 
          + "____________________________________";
      System.out.println(line);
      System.out.println("    Here are the tasks in your list:");
      for (int i = 0; i < count; i++) {
        int listNumber = i + 1;
        System.out.println("    " + listNumber + "." + list[i]);
      }
      System.out.println(line + "\n");
    }

    static void markItemComplete(int index) {
      String line = "    ________________________" 
          + "____________________________________";
      Task t = list[index - 1];
      t.complete();
      System.out.println(line);
      System.out.println("     Nice! I've marked this task as done:");
      System.out.println("       " + t);
      System.out.println(line + "\n");
    }

    static Task addToList(String s, String type) {
      if (type.equals("todo")) {
        list[count] = new Todo(s, count + 1);
      } else if (type.equals("deadline")) {
        String[] parts = s.split("\\/" + "by");
        list[count] = new Deadline(parts[0], count + 1, parts[1]);
      } else if (type.equals("event")) {
        String[] parts = s.split("\\/" + "at");
        list[count] = new Event(parts[0], count + 1, parts[1]);
      }
      count++;
      return list[count - 1];
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
        System.out.println("       " + t);
        System.out.println("     Now you have "+count+" tasks in the list.");
        System.out.println(line + "\n");
        return;
    }
}
