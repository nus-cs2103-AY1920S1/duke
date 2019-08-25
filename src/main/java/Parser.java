import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Parser {

  private static String getSuffix(LocalDateTime dateTime) {
    int day = dateTime.getDayOfMonth();
    int remainder = day % 10;
    if (remainder == 1) {
      return "st";
    } else if (remainder == 2) {
      return "nd";
    } else if (remainder == 3) {
      return "rd";
    } else {
      return "th";
    }
  }

  private static String getDate(String dateTimeString) throws DateTimeParseException {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
    String suffix = getSuffix(dateTime) + " of";

    DateTimeFormatter wantedFormat = DateTimeFormatter.ofPattern(" d'" + suffix + "' MMMM yyyy, h.mm a");
    String formattedDate = wantedFormat.format(dateTime);
    return formattedDate;
  }

  private static void printTasks(ArrayList<Task> t) {
    System.out.println("\t____________________________________________________________");
    System.out.println("\n\tHere are the tasks in your list: ");
    for (int i = 0; i < t.size(); i++) {
      System.out.println("\n\t" + (i + 1) + ". " + t.get(i).toString());
    }
    System.out.println("\t____________________________________________________________\n");
  }

  private String command;
  private String taskDescription;
  private int taskNum;
  private String date;
  private String dateBeforeFormat;

  public Parser(String command, String description)
      throws DukeException, NumberFormatException, DateTimeParseException {

    try {

      if (command.equals("list")) {
        this.command = command;
      } else if (command.equals("done")) {
        this.command = command;
        this.taskNum = Integer.valueOf(description);
      } else if (command.equals("bye")) {
        this.command = command;
      } else if (command.equals("todo")) {
        this.command = command;
        this.taskDescription = description;
      } else if (command.equals("deadline")) {
        this.command = command;
        String[] wordArr = description.split("/by", 2);
        if (wordArr.length == 1) {
          throw new DukeException("OOPS! Deadlines should be followed by a /by.");
        }
        this.taskDescription = wordArr[0];
        this.dateBeforeFormat = wordArr[1];
        this.date = getDate(wordArr[1].stripLeading());
      } else if (command.equals("event")) {
        this.command = command;
        String[] wordArr = description.split("/at", 2);
        if (wordArr.length == 1) {
          throw new DukeException("OOPS! Deadlines should be followed by a /at.");
        }
        this.taskDescription = wordArr[0];
        this.dateBeforeFormat = wordArr[1];
        this.date = getDate(wordArr[1].stripLeading());
      } else if (command.equals("delete")) {
        this.command = command;
        this.taskNum = Integer.valueOf(description);
      } else {
        throw new DukeException("OOPS! I'm sorry, I don't know what that means! :(");
      }
    } catch (DukeException e) {
      System.out.println(e.getMessage());
    } catch (NumberFormatException e) {
      System.out.println("OOPS! An integer is expected after done / delete!");
    } catch (DateTimeParseException e) {
      System.out.println("OOPS! Dates should be in the format mm/dd/yyyy (24-hour time format)");
    }
  }

  public void executeOnly(TaskList taskList) throws DukeException, IOException {

    ArrayList<Task> tasks = taskList.getTaskList();

    if (command.equals("todo")) {
      if (taskDescription == "") {
        throw new DukeException("OOPS! The description for todo should not be empty.");
      }
      ToDo task = new ToDo(taskDescription);
      tasks.add(task);

    } else if (command.equals("deadline")) {

      DeadLine task = new DeadLine(taskDescription, date);
      tasks.add(task);

    } else if (command.equals("event")) {

      Event task = new Event(taskDescription, date);
      tasks.add(task);

    } else if (command.equals("delete")) {
      if (taskNum < 0 || taskNum >= tasks.size()) {
        throw new DukeException("OOPS! Integer is out of range of list.");
      }
      Task removed = tasks.get(taskNum);
      tasks.remove(taskNum);

    } else if (command.equals("done")) {
      if (taskNum < 0 || taskNum >= tasks.size()) {
        throw new DukeException("OOPS! Integer is out of range of list.");
      }
      tasks.get(taskNum).setAsDone();

    } else if (command.equals("bye")) {
      return;
    } else if (command.equals("list")) {
      taskList.printTasks();
    } else {
      throw new DukeException("OOPS! I'm sorry, I don't know what that means! :(");
    }

    taskList.setTaskList(tasks);
  }

  public void executeAndSave(TaskList taskList, Storage s) throws DukeException, IOException {

    ArrayList<Task> tasks = taskList.getTaskList();

    if (command.equals("todo")) {
      if (taskDescription == "") {
        throw new DukeException("OOPS! The description for todo should not be empty.");
      }
      ToDo task = new ToDo(taskDescription);
      tasks.add(task);

      // Printing Output
      System.out.println("\t____________________________________________________________");
      System.out.println("\n\tGot it! I've added this task: ");
      System.out.println("\n\t" + task.toString());
      System.out.println("\n\tNow you have " + tasks.size() + " tasks in the list.");
      System.out.println("\t____________________________________________________________\n");

      // Save the command in taskList.txt
      String text = "todo " + taskDescription + "\n";
      s.appendToFile(text);

    } else if (command.equals("deadline")) {

      DeadLine task = new DeadLine(taskDescription, date);
      tasks.add(task);

      System.out.println("\t____________________________________________________________");
      System.out.println("\n\tGot it! I've added this task: ");
      System.out.println("\n\t" + task.toString());
      System.out.println("\n\tNow you have " + tasks.size() + " tasks in the list.");
      System.out.println("\t____________________________________________________________\n");

      // Save the command in taskList.txt
      String text = "deadline " + taskDescription + " /by " + dateBeforeFormat + "\n";
      s.appendToFile(text);

    } else if (command.equals("event")) {

      Event task = new Event(taskDescription, date);
      tasks.add(task);

      System.out.println("\t____________________________________________________________");
      System.out.println("\n\tGot it! I've added this task: ");
      System.out.println("\n\t" + task.toString());
      System.out.println("\n\tNow you have " + tasks.size() + " tasks in the list.");
      System.out.println("\t____________________________________________________________\n");

      // Save the command in taskList.txt
      String text = "event " + taskDescription + " /at " + dateBeforeFormat + "\n";
      s.appendToFile(text);

    } else if (command.equals("delete")) {
      if (taskNum < 0 || taskNum >= tasks.size()) {
        throw new DukeException("OOPS! Integer is out of range of list.");
      }
      Task removed = tasks.get(taskNum);
      tasks.remove(taskNum);

      System.out.println("\t____________________________________________________________");
      System.out.println("\n\tNoted. I have removed this task: ");
      System.out.println("\n\t" + removed);
      System.out.println("\n\tNow you have " + tasks.size() + " tasks in the list.");
      System.out.println("\t____________________________________________________________\n");

      // Save the command in taskList.txt
      String text = "delete " + taskNum + "\n";
      s.appendToFile(text);

    } else if (command.equals("done")) {
      if (taskNum < 0 || taskNum >= tasks.size()) {
        throw new DukeException("OOPS! Integer is out of range of list.");
      }
      tasks.get(taskNum).setAsDone();
      System.out.println("\t____________________________________________________________");
      System.out.println("\n\tNice! I have marked this task as done: ");
      System.out.println("\n\t" + tasks.get(taskNum));
      System.out.println("\t____________________________________________________________\n");

      // Save the command in taskList.txt
      String text = "done " + taskNum + "\n";
      s.appendToFile(text);

    } else if (command.equals("bye")) {
      System.out.println("\t____________________________________________________________");
      System.out.println("\n\tBye. Hope to see you again soon!");
      System.out.println("\t____________________________________________________________\n");
      return;
    } else if (command.equals("list")) {
      taskList.printTasks();
    } else {
      throw new DukeException("OOPS! I'm sorry, I don't know what that means! :(");
    }

    taskList.setTaskList(tasks);
  }

}