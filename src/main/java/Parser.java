import java.util.Scanner;

public class Parser {
    private String command;
    private String nextCommand;
    private String taskBy;
    private int taskNum;
    public void parse(String input) throws DukeException{
        if (input.equals("")) {
            throw new DukeException("The State demands that you speak!");
        }
        Scanner sc = new Scanner(input);
        command = sc.next();
        if(command.equals("delete") || command.equals("done")) {
            if (!sc.hasNext()) {
                throw new DukeException("Comrade? Which one to remove?");
            }
            taskNum = sc.nextInt();
        } else if (command.equals("todo") || command.equals("deadline") || command.equals("event")
        || command.equals("find")) {
            if (!sc.hasNext()) {
                throw new DukeException("Comrade? You did not finish your sentence!");
            }
            String next = sc.next();
            nextCommand = "";
            while (sc.hasNext()) {
                if (next.equals("/at") || next.equals("/by")) {
                    break;
                }
                nextCommand += " " + next;
                next = sc.next();
            }
            if (command.equals("deadline") || command.equals("event")) {
                if (!sc.hasNext()) {
                    throw new DukeException("Comrade! Give commands in the right format!");
                }
                taskBy = sc.nextLine();
            }
        }
    }
    public int getTaskNum() {
        return taskNum;
    }
    public String getCommand() {
        return command;
    }
    public String getNextCommand() {
        return nextCommand;
    }
    public String getDoByDate() {
        return taskBy;
    }
    public void scrubData() {
        command = "";
        nextCommand = "";
        taskBy = "";
    }
}
