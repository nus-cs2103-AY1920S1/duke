public class Deadline extends Task   {
    private String deadlineTime;

    public Deadline(String name) {
        super(name);
    }

    public Deadline(String name, String dT) {
        super(name);
        this.deadlineTime = dT;
    }

    public String toString() {
        return "D|" + super.toString().trim() + "|" + deadlineTime.trim();

    }

    public void formatDeadlineTime(String dT) throws DukeException {
        System.out.println(dT);
        String format = dT.substring(0,2) + ":" + dT.substring(2);
        this.deadlineTime = format;
        System.out.println(dT.substring(2).trim());
       //this.dateTime = formatDateTime(dT.substring(2).trim());
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
