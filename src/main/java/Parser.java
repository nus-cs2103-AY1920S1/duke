import java.util.Scanner;
import java.util.HashMap;
import java.util.Date;

public class Parser {
    protected static HashMap<String, Command> keywordToCommand;

    public Parser() {
        keywordToCommand = new HashMap<>();
        keywordToCommand.put("bye", new ExitCommand());
        keywordToCommand.put("list", new ListCommand());
        keywordToCommand.put("done", new DoneCommand());
        keywordToCommand.put("delete", new DeleteCommand());
        keywordToCommand.put("todo", new AddCommand());
        keywordToCommand.put("deadline", new AddCommand());
        keywordToCommand.put("event", new AddCommand());
    }

    public static Command parse(String input) throws DukeException {
        if(keywordToCommand.get(input) != null) {
            return keywordToCommand.get(input);
        } else {
            Scanner scanner = new Scanner(input);
            String keyword = scanner.next();
            scanner.close();
            Command command;
            try {
                command = keywordToCommand.get(keyword).clone(input);
            } catch(NullPointerException e) {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            return command;
        }
    }

    public static int parseDone(String input) throws AssertionError {
        Scanner scanner = new Scanner(input);
        
        String command = scanner.next();
        assert(command.equals("done"));

        int itemId = scanner.nextInt();
        scanner.close();
        return itemId;
    }

    public static int parseDelete(String input) throws AssertionError {
        Scanner scanner = new Scanner(input);
        
        String command = scanner.next();
        assert(command.equals("delete"));

        int itemId = scanner.nextInt();
        scanner.close();
        return itemId;
    }

    public static Task parseTask(String input) throws DukeException {
        Scanner scanner = new Scanner(input);
        String type = scanner.next();
        scanner.close();

        switch(type) {
        case "todo":
            return createTodo(input.substring("todo".length()));
        case "deadline":
            return createDeadline(input.substring("deadline".length()));
        case "event":
            return createEvent(input.substring("event".length()));
        }
        throw new DukeException("Invalid task input.");
    }

    public static Todo createTodo(String input) throws DukeException {
        String description = input.length() == 0
                ? input
                : input.substring(1);
        if(description.equals("")) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        return new Todo(description);
    }

    public static Deadline createDeadline(String input) {
        StringBuffer stringBuffer = new StringBuffer();
        Scanner scanner = new Scanner(input);

        String temp = scanner.next();
        while(!temp.equals("/by")) {
            stringBuffer.append(" " + temp);
            temp = scanner.next();
        }

        String description = stringBuffer.length() == 0
                ? stringBuffer.toString()
                : stringBuffer.toString().substring(1);

        String date = scanner.nextLine();

        scanner.close();
        return new Deadline(description, parseDate(date));
    }

    public static Event createEvent(String input) {
        StringBuffer stringBuffer = new StringBuffer();
        Scanner scanner = new Scanner(input);
        
        String temp = scanner.next();
        while(!temp.equals("/at")) {
            stringBuffer.append(" " + temp);
            temp = scanner.next();
        }

        String description = stringBuffer.length() == 0
                ? stringBuffer.toString()
                : stringBuffer.toString().substring(1);

        String date = scanner.nextLine();

        scanner.close();
        return new Event(description, parseDate(date));
    }

    public static Date parseDate(String date) {
        String[] dayAndTime = date.trim().split(" ");
        String[] ddMmYy = dayAndTime[0].split("/");
        String hhSs = dayAndTime[1];
        int day = Integer.parseInt(ddMmYy[0]);
        int month = Integer.parseInt(ddMmYy[1]);
        int year = Integer.parseInt(ddMmYy[2]);
        int hour = Integer.parseInt(hhSs.substring(0, 2));
        int min = Integer.parseInt(hhSs.substring(2));
        System.out.println(String.format("%d %d %d %d %d", day, month, year, hour, min));
        return new Date(year - 1900, month - 1, day, hour, min);
    } 
}