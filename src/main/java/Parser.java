import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Parser {
    private String input;

    public Parser(String input) {
        this.input = input;
    }

    public TaskType getInputTaskType() throws DukeException{
        String keyword = this.input.split("\\s+")[0];
        TaskType taskType = TaskType.convertToTaskType(keyword);
        if (taskType == null) {
            throw new InvalidKeywordException(keyword);
        }
        return taskType;
    }

    public String getInputEntireDescription() {
        Scanner sc = new Scanner(this.input);
        sc.next(); //scan past taskType
        String description = "";
        if (sc.hasNext()) {
            description = sc.nextLine().trim();
        }
        sc.close();
        return description;
    }

    public static Command parse(String fullCommand) throws DukeException {
        if (fullCommand.equals("")) {
            throw new DukeException("Please give me a command master! (You didn't input any command, but entered...");
        }

        Parser parser = new Parser(fullCommand);
        TaskType taskType = parser.getInputTaskType();
        String descr = parser.getInputEntireDescription();
        Command cmd;
        switch (taskType) {
        case LIST:
            cmd = new ListCommand();
            break;
        case DELETE:
            cmd = new DeleteCommand(descr);
            break;
        case DONE:
            cmd = new DoneCommand(descr);
            break;
        case EXIT:
            cmd = new ExitCommand();
            break;
        default: //add or wrong
            cmd = new AddCommand(taskType, descr);
        }

        return cmd;
    }

    public String getDescriptionWithoutDate() { //only if input is entire description alr; aka for indiv Task classes
        return "";
    }

    public Date convertToDate() {
        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy HHmm").parse(this.input);
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            return date;
        }
    }

    public static int parseStrToInt(String input) throws DukeException {
        int id = 0;
        try {
            id = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidIDException(input);
        }
        return id;
    }
}
