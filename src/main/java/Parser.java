import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Parser {

    private static int indexOfByAt = 0;
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
    private DateFormat printFormat = new SimpleDateFormat("dd/MM/yyyy HH.mm aa");
    private static Date dateTime;

    public Parser() {
    }

    public static Date dateParse(String str) throws ParseException {
        return dateFormat.parse(str);
    }

    public String formatDate(Date d) {
        return dateFormat.format(d);
    }

    public String formatPrint(Date d) {
        return printFormat.format(d);
    }

    public static Command parse(String command, Ui ui) throws DukeException, ParseException {
        if (command.equals("bye")) {
            return new ExitCommand();
        }
        String[] detailsArray = command.split(" ");
        if (detailsArray.length == 1 && (detailsArray[0].equals("todo") || detailsArray[0].equals("deadline") ||
                detailsArray[0].equals("event"))) {
            throw new EmptyDescriptionException(ui.emptyDescriptionMsg(detailsArray[0]));
        } else {
            switch (detailsArray[0]) {
            case "todo":
                return new TodoCommand(String.join(" ",
                        Arrays.copyOfRange(detailsArray, 1, detailsArray.length)));
            case "deadline":
                getDate(detailsArray);
                return new DeadlineCommand(String.join(" ",
                        Arrays.copyOfRange(detailsArray, 1, indexOfByAt)), dateTime);
            case "event":
                getDate(detailsArray);
                return new EventCommand(String.join(" ",
                        Arrays.copyOfRange(detailsArray, 1, indexOfByAt)), dateTime);
            case "delete":
                return new DeleteCommand(Integer.parseInt(detailsArray[1]) - 1);
            case "done":
                return new DoneCommand(Integer.parseInt(detailsArray[1]) - 1);
            case "list":
                return new ListCommand();
            default:
                throw new InvalidInputException(ui.invalidInputMsg());
            }
        }
    }


    private static void getDate(String[] detailsArray) throws ParseException {
        //Date dateTime = null;
        if (detailsArray[0].equals("deadline")) {
            for (int i = 0; i < detailsArray.length; i++) {
                if (detailsArray[i].equals("/by")) {
                    dateTime = dateParse(String.join(" ",
                            Arrays.copyOfRange(detailsArray, i + 1, detailsArray.length)));
                    indexOfByAt = i;
                    break;
                }
            }
        } else {
            for (int i = 0; i < detailsArray.length; i++) {
                if (detailsArray[i].equals("/at")) {
                    dateTime = dateParse(String.join(" ",
                            Arrays.copyOfRange(detailsArray, i + 1, detailsArray.length)));
                    indexOfByAt = i;
                    break;
                }
            }
        }
    }

}
