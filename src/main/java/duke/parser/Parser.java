package duke.parser;

import duke.command.*;
import duke.exception.DukeException;

public class Parser {
  private static final String unknownCommand = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
  private static final String unknownTodo = "☹ OOPS!!! The description of a todo cannot be empty.";
  private static final String unknownDeadline = "☹ OOPS!!! The description of a deadline cannot be empty.";
  private static final String unknownEvent = "☹ OOPS!!! The description of an event cannot be empty.";

  public static Command parse(String line) throws DukeException {
    if (line.equals("bye")) {
      return new ByeCommand();
    } else if (line.equals("list")) {
      return new ListCommand();
    } else {
      String[] strArr = line.split(" ");
      String command = strArr[0];
      int index;
      switch (command) {
      case "todo":
        String todo = toDoString(strArr);
        return new AddCommand("todo", todo);
      case "deadline":
        String[] deadline = deadlineEventString(strArr, true);
        return new AddCommand("deadline", deadline[0], deadline[1]);
      case "event":
        String[] event = deadlineEventString(strArr, false);
        return new AddCommand("event", event[0], event[1]);
      case "done":
        index = Integer.parseInt(strArr[1]);
        return new DoneCommand(index);
      case "delete":
        index = Integer.parseInt(strArr[1]);
        return new DeleteCommand(index);
      default:
        throw new DukeException(unknownCommand);
      }
    }
  }

  private static String[] deadlineEventString(String[] arr, boolean deadline) throws DukeException {
    String[] res = new String[2];
    StringBuilder sb = new StringBuilder();
    int divide = 0;
    for (int i = 1; i < arr.length; i++) {
      if (deadline && arr[i].equals("/by") || !deadline && arr[i].equals("/at")) {
        divide = i;
        break;
      }
      sb.append(arr[i]);
      sb.append(" ");
    }
    res[0] = sb.toString().trim();
    if (res[0].isEmpty())
      throw new DukeException(deadline ? unknownDeadline: unknownEvent);
    sb = new StringBuilder();
    for (int i = divide + 1; i < arr.length; i++) {
      sb.append(arr[i]);
      sb.append(" ");
    }
    res[1] = sb.toString().trim();
    return res;
  }

  private static String toDoString(String[] arr) throws DukeException {
    StringBuilder sb = new StringBuilder();
    for (int i = 1; i < arr.length; i++) {
      sb.append(arr[i]);
      sb.append(" ");
    }
    String res = sb.toString().trim();
    if (res.isEmpty())
      throw new DukeException(unknownTodo);
    return res;
  }
}
