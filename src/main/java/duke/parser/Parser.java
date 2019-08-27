package duke.parser;

import duke.command.*;
import duke.exception.DukeException;

/**
 * Represents a parser that parses a line, returning a Command instance
 */
public class Parser {
  private static final String unknownCommand = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
  private static final String unknownTodo = "☹ OOPS!!! The description of a todo cannot be empty.";
  private static final String unknownDeadline = "☹ OOPS!!! The description of a deadline cannot be empty.";
  private static final String unknownEvent = "☹ OOPS!!! The description of an event cannot be empty.";

  /**
   * Returns a Command instance of the specified line.
   *
   * @param line Line to be parsed.
   * @return an instance of a Command specified by the line .
   * @throws DukeException when line does not meet expected parameters.
   */
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
        return new AddCommand("todo", line.substring(5));
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

  /**
   * Helper method that returns a string for Event or Deadline instance creation.
   * @param arr String Array.
   * @param deadline true if deadline, false if event.
   * @return String[] with 2 elements - first is name, second is datetime.
   * @throws DukeException if expected parameters are not met.
   */
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
}
