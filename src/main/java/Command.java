public abstract class Command {
    private String details;

    public static Command addTodoCommand(String details) throws DukeEmptyDescriptionException {
        return new TodoCommand(details);
    }

    public static Command addDeadlineCommand(String details) throws DukeMissingDescriptionException, DukeEmptyDescriptionException  {
        return new DeadlineCommand(details);
    }

    public static Command addEventCommand(String details) throws DukeMissingDescriptionException, DukeEmptyDescriptionException{
        return new EventCommand(details);
    }

    public static Command addListCommand() {
        return new ListCommand();
    }

    public static Command addDoneCommand(String details) throws DukeEmptyDescriptionException {
        return new DoneCommand(details);
    }

    public static Command addDeleteCommand(String details) throws DukeEmptyDescriptionException {
        return new DeleteCommand(details);
    }

    public static Command addByeCommand() {
        return new ByeCommand();
    }

    public Command(String details) {
        this.details = details;
    }

    public Command() {
        this.details = "";
    }

    public String getDetails() {
        return this.details;
    }

    public static String dateToWords(String dateFull) {
        String[] date = dateFull.split("/");
        String month;
        switch(date[1]) {
            case "1":
                month = "January";
                break;

            case "2":
                month = "Febuary";
                break;

            case "3":
                month = "March";
                break;

            case "4":
                month = "April";
                break;

            case "5":
                month = "May";
                break;

            case "6":
                month = "June";
                break;

            case "7":
                month = "July";
                break;

            case "8":
                month = "August";
                break;

            case "9":
                month = "September";
                break;

            case "10":
                month = "October";
                break;

            case "11":
                month = "November";
                break;

            case "12":
                month = "December";
                break;

            default:
                month = "month";
                break;
        }
        String day = date[0].equals("1") ? date[0] + "st" : date[0].equals("2") ? date[0] + "nd" :
                date[0].equals("3") ? date[0] + "rd" : date[0] + "th";
        return day + " of " + month + " 20" + date[2];
    }

    public static String timeConverter(String time) {
        int numberTime = Integer.parseInt(time);
        if (numberTime < 1000) {
            return String.valueOf(numberTime).substring(0, 1) + "." + String.valueOf(numberTime).substring(1, 3) + "am";
        } else if (numberTime < 1200) {
            return String.valueOf(numberTime).substring(0, 2) + "." + String.valueOf(numberTime).substring(2, 4) + "am";
        } else if (numberTime < 1300) {
            return String.valueOf(numberTime).substring(0, 2) + "." + String.valueOf(numberTime).substring(2, 4) + "pm";
        } else {
            numberTime -= 1200;
            if (numberTime < 1000) {
                return String.valueOf(numberTime).substring(0, 1) + "." + String.valueOf(numberTime).substring(1, 3) + "pm";
            } else {
                return String.valueOf(numberTime).substring(0, 2) + "." + String.valueOf(numberTime).substring(2, 4) + "pm";
            }
        }
    }

    public abstract void execute(TaskList tasks, DukeUI ui, StorageData storage);
}
