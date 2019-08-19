import java.util.Scanner;

public class Duke {
    private static String[] list = new String[100];
    private static int count = 0;
    public static void main(String[] args) {
        welcomeMessage();
        Scanner sc = new Scanner(System.in);
        while (true) {
          String input = sc.nextLine();
          if (input.equals("bye")) {
            byeMessage();
            break;
          } else if (input.equals("list")) {
            displayList();
          } else {
            addToList(input);
            echo(input);
          }
        }
        sc.close();
    }

    static void displayList() {
      String line = "    ________________________" 
          + "____________________________________";
      System.out.println(line);
      for (int i = 0; i < count; i++) {
        int index = i + 1;
        System.out.println("    " + index + ". " + list[i]);
      }
      System.out.println(line);
    }

    static void addToList(String s) {
      list[count] = s;
      count++;
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
    
    public static void echo(String s) {
        String line = "    ________________________" 
            + "____________________________________";
        System.out.println(line);
        System.out.println("     added: " + s);
        System.out.println(line + "\n");
        return;
    }
}
