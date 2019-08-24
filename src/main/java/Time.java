public class Time {
    protected String unprocessedTime;
    protected String processedTime;
    protected boolean isAfternoon = false;

    public Time(String unprocessedTime) throws DukeException{
        this.unprocessedTime = unprocessedTime;
        processTime();
    }

    public void processTime() throws DukeException {
        if (unprocessedTime.equals("")) {
            return;
        }
        if (unprocessedTime.length() == 4) {
            int time = Integer.parseInt(unprocessedTime);
            int hour = time / 100;
            int minute = time % 100;
            if (hour > 23 || minute > 59) {
                throw new DukeException("\u2639 OOPS!!! Please input a valid time.");
            }
            if (hour > 11) {
                hour = hour % 12;
                isAfternoon = true;
            }
            if (hour == 0) {
                hour = 12;
            }
            StringBuilder timing = new StringBuilder();
            timing.append(hour);
            if (minute != 0) {
                if (minute < 10) {
                    timing.append(":" + "0" + minute);
                } else {
                    timing.append(":" + minute);
                }
            }
            processedTime = timing.toString();
        } else {
            throw new DukeException("\u2639 OOPS!!! Please input a valid time e.g. 1800.");
        }
    }

    @Override
    public String toString() {
        if (processedTime == null) {
            return "";
        }
        if (isAfternoon) {
            return processedTime + "pm";
        } else {
            return processedTime + "am";
        }
    }
}
