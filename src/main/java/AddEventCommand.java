public class AddEventCommand extends Command {

    public AddEventCommand(String message) {
        super(message);
    }

    @Override
    public void execute(TaskList listOfTasks, Storage storage, UI ui) throws Exception {
        String[] inputMessage = input.split(" ");
        String mainInput = "";
        int marker = 0;
        for (int i = 0; i < inputMessage.length; i++) {
            if (i + 1 >= inputMessage.length) {
                throw new DukeException("     Please provide more information");
            }
            if (inputMessage[i + 1].equals("/at")) {
                mainInput += inputMessage[i];
                marker = i + 1;
                break;
            } else {
                mainInput += inputMessage[i];
                mainInput += " ";
            }
        }
        String extraInfo = "";
        String tempInfo = "";
        for (int i = marker + 1; i < inputMessage.length; i++) {
            if (i == inputMessage.length - 1) {
                tempInfo += inputMessage[i];
            } else {
                tempInfo += inputMessage[i];
                tempInfo += " ";
            }
        }
        String[] dateAndTime = tempInfo.split(" ");
        String[] date = dateAndTime[0].split("/");
        if (date.length != 3 || dateAndTime.length < 2) {
            throw new DukeException("     Invalid date and time format!");
        }
        if (date[0].equals("") || date[1].equals("") || date[2].equals("")) {
            throw new DukeException("     Invalid date and time format!");
        }
        int day = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int year = Integer.parseInt(date[2]);
        Date inputDate = new Date(day, month, year);
        Time inputTime = new Time(Integer.parseInt(dateAndTime[1]));
        if (!inputDate.isValid()) {
            throw new DukeException("     Sorry! Invalid date format");
        }
        if (!inputTime.isValid()) {
            throw new DukeException("     Sorry! Invalid time format");
        }
        extraInfo = inputDate + ", " + inputTime;
        if (extraInfo.equals("")) {
            throw new DukeException("     Please provide date and time of the deadline");
        }
        if (!inputMessage[marker].equals("/at")) {
            throw new DukeException("     Wrong syntax, should be using /at for deadline");
        }
        listOfTasks.addEvent(new Event(input, extraInfo));
        storage.updateTaskList(listOfTasks.getTasks());
        storage.writeToFile();
        ui.printTaskAdd(listOfTasks.get(listOfTasks.size() - 1));
    }
}
