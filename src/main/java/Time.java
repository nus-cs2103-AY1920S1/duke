public class Time {
    protected int time;
    protected String outputTime;
    protected boolean validFormat = true;
    public Time(int time) throws DukeException {
        String timeString = String.valueOf(time);
        if (timeString.length() != 4) {
            validFormat = false;
        }
        String format = "";
        if (time >= 1200) {
            format = "pm";
        } else {
            format = "am";
        }
        int hours = time / 100;
        hours = hours % 12;
        int minutes = time % 100;
        if (minutes > 60) {
            throw new DukeException("     Invalid minute format!");
        }
        String hour = String.valueOf(hours);
        String minute = "";
        if (minutes != 0) {
            minute = "." + minutes;
        }
        outputTime = hour + minute + format;
    }

    public boolean isValid() {
        return validFormat;
    }
    @Override
    public String toString() {
        return outputTime;
    }
}
