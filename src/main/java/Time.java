public class Time {
    private int hour;
    private int minute;

    public Time(String format24h) {
        this.hour = Integer.parseInt(format24h.substring(0, 2));
        this.minute = Integer.parseInt(format24h.substring(2));
    }

    @Override
    public String toString() {
        StringBuilder timeString = new StringBuilder();
        if(this.hour >= 12) {
            if(this.hour > 12) {
                timeString.append(this.hour - 12);
            } else {
                timeString.append(this.hour);
            }
            if(this.minute != 0) {
                timeString.append(this.minute);
            }
                timeString.append("pm");
        } else {
            timeString.append(this.hour);
            if (this.minute != 0) {
                timeString.append(this.minute);
            }
            timeString.append("am");
        }
        return timeString.toString();
    }
}
