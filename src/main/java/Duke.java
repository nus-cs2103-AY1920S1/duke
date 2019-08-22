import java.util.*;

public class Duke {
  private static ArrayList<Task> storage = new ArrayList<>();
  private static Scanner sc = new Scanner(System.in);

  private static void addTask(Task t) {
    System.out.println("Got it. I've added this task:\n" + t.toString());
    storage.add(t);
    System.out.println(
      "Now you have " + storage.size() + " tasks in your list."
    );
  }

  private static boolean handleCommand(String in) throws DukeException {
    switch (in) {
      case "bye":
        System.out.println("Bye!");
        return false;
      case "list":
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= storage.size(); i++) {
          Task t = storage.get(i - 1);
          System.out.println(String.format("%d. %s", i, t.toString()));
        }
        break;
      case "done":
        {
          Task t = storage.get(sc.nextInt() - 1);
          t.markDone();
          System.out.println(
            String.format(
              "Nice! I've marked this task as done:\n  %s",
              t.toString()
            )
          );
          break;
        }
      case "todo":
        {
          String details = sc.nextLine().trim();
          if (details.isEmpty()) {
            throw new DukeException("Todo name cannot be empty!");
          }
          addTask(new ToDo(details));
          break;
        }
      case "deadline":
        {
          String[] details = sc.nextLine().trim().split(" /by ");
          try {
            addTask(new Deadline(details[0], details[1]));
          } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Too few details for deadline!");
          }
          break;
        }
      case "event":
        {
          String[] details = sc.nextLine().trim().split(" /at ");
          try {
            addTask(new Event(details[0], details[1]));
          } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Too few details for event!");
          }
          break;
        }
      default:
        throw new DukeException("Unknown command " + in);
    }
    return true;
  }

  public static void main(String[] args) {
    String logo = " ____        _        \n" +
      "|  _ \\ _   _| | _____ \n" +
      "| | | | | | | |/ / _ \\\n" +
      "| |_| | |_| |   <  __/\n" +
      "|____/ \\__,_|_|\\_\\___|\n";
    System.out.println("Hello from\n" + logo);
    String in;
    boolean status = true;
    while (status) {
      try {
        in = sc.next();
      } catch (NoSuchElementException e) {
        break; // input stream has ended.
      }
      try {
        status = handleCommand(in);
      } catch (DukeException e) {
        System.out.println(e.toString());
      }
    }
  }
}
