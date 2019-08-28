import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Parser {

    String time;
    String name;
    boolean timing;
    boolean firstInName;
    boolean firstInTime;
    Task task = null;
    static int count = 0;

    public Parser(){};

    public Command process(String line) {
        String[] commands = line.split(" ");
        String first = commands[0];
        try {
            switch (first) {
                case "bye":
                    System.out.println("was here");
                    return new Command(CommandType.EXIT);
                case "list":
                    return new Command(CommandType.PRINTLIST);
                case "todo":
                case "deadline":
                case "event":
                    return new Command(CommandType.ADD, line);
                case "done":
                   return new Command(CommandType.DONE, line);
                case "delete":
                    return new Command(CommandType.DELETE, line);
                default:
                    throw new InvalidCommandException();
            }
        } catch (DukeException e) {
            System.out.println("at error");
            e.printError();
        }
        System.out.println("here WHICH IS WRONG");
        return new Command();
    }

    public Task createTask(String line) {
        String[] description = line.split(" ");
        String eventType = description[0];
        try {
            if (description.length <= 1) {
                throw new MissingInputException(eventType);
            }
        } catch (MissingInputException e) {
            e.printError();
        }
        count++;
        return createNewTask(count, eventType, description);
    }

    public int getTaskNo(String line) {
        String[] description = line.split(" ");
        String eventType = description[0];
        try {
            if (description.length <= 1) {
                throw new MissingInputException(eventType);
            }
        } catch (MissingInputException e) {

        }
        return Integer.parseInt(description[1]);
    }

    public Task createNewTask(int taskNo, String info, String...arr) {
        time = "";
        name = "";
        timing = false;
        firstInName = true;
        firstInTime = true;
        for (int i = 1; i < arr.length; i++) {
            System.out.println(arr[i]);
            if (arr[i].startsWith("/")) {
                timing = true;
            } else {
                if (firstInName) {
                    name = arr[i];
                    firstInName = false;
                } else if (timing && firstInTime) {
                    time = arr[i];
                    firstInTime = false;
                } else if (timing) {
                    time += " " + arr[i];
                } else {
                    name += " " + arr[i];
                }
            }
        }
        switch (info) {
            case "todo":
                task = new Todo(taskNo, name, "T");
                break;
            case "deadline":
                task = new Deadline(taskNo, name, time, "D");
                break;
            case "event":
                task = new Event(taskNo, name, time, "E");
                break;
        }
        return task;
    }

}
