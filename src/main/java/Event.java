public class Event extends Task {
    private String eventTime;
    private String dateTime;

    public Event(String name) {
        super(name);
    }

    public Event(String name, String eT) throws DukeException{
        super(name);
        this.eventTime = eT;
        formatEventTime(eT);
    }
    public void formatEventTime(String eT) throws DukeException {
        String format = eT.substring(0,2) + ":" + eT.substring(2);
        this.eventTime = format;
        this.dateTime = formatDateTime(eT.substring(2).trim());
    }


    public String toString() {
        return "E|" + super.toString().trim() + "|" + dateTime.trim();

    }

    private String formatDateTime(String details) throws DukeException {
        StringBuilder formattedDateTime = new StringBuilder();
        try {
            String[] split = details.split(" ");
            String date = split[0];
            String time = split[1];
            String splitDate[] = date.split("/");
            formattedDateTime.append(String.format("%02d", Integer.parseInt(splitDate[0])));
            formattedDateTime.append("/");
            formattedDateTime.append(String.format("%02d", Integer.parseInt(splitDate[1])));
            formattedDateTime.append("/");
            formattedDateTime.append(splitDate[2]);
            formattedDateTime.append(" ");
            formattedDateTime.append(time);
            return formattedDateTime.toString();
        } catch (Exception ex) {
            throw new DukeException("the date time format entered is incorrect. " +
                    "Please enter again in the following format: dd/MM/yyyy HHmm");
        }
    }
}
