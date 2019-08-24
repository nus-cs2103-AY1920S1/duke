package parser;

public class Parser {
  public static Command parse(String command) throws DukeException {
    if (command.equals("bye")) {
      System.out.println("Bye. Hope to see you again soon!");
    } else if (command.equals("list")) {
      if (list.isEmpty()) {
        System.out.println("There are no tasks on your list!");
      }
      int count = 1;
      System.out.println("Here are the tasks in your list:");
      for (Task task : list) {
        System.out.println("" + count + "." + task);
        count++;
      }
    } else {
      String[] strArr = command.split(" ");
      String command = strArr[0];
      Task t;
      int index;
      try {
        switch (command) {
          case "todo":
            String todo = toDoString(strArr);
            t = new ToDo(todo);
            list.add(t);
            taskAdded(t, list.size());
            break;
          case "deadline":
            String[] deadline = deadlineEventString(strArr, true);
            t = new Deadlines(deadline[0], deadline[1]);
            list.add(t);
            taskAdded(t, list.size());
            break;
          case "event":
            String[] event = deadlineEventString(strArr, false);
            t = new Event(event[0], event[1]);
            list.add(t);
            taskAdded(t, list.size());
            break;
          case "done":
            index = Integer.parseInt(strArr[1]);
            String current = list.get(index - 1).storageString();
            list.get(index - 1).setDone();
            String res = list.get(index - 1).storageString();
            replaceLine(current, res);
            break;
          case "delete":
            index = Integer.parseInt(strArr[1]);
            Task deleted = list.remove(index - 1);
            deleteLine(deleted.storageString());
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + deleted);
            System.out.println("Now you have " + list.size() + " tasks in the list.");
            break;
          default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
      }
    }
  }
}
