public class Timing {
    private int hour;
    private int minute;
    private String timeString;
    private boolean meridiemFlag = false; //to check whether AM or PM

    public Timing(String timeString){
        this.timeString = timeString;
        int timeInt = Integer.parseInt(timeString);
        this.minute = timeInt % 100;
        this.hour = timeInt / 100;

        if(this.hour >= 12){
            meridiemFlag = true; //means it is PM not AM
        }
    }

    public String getTimeString(){
        return timeString;
    }

    @Override
    public String toString(){
        String toReturn = (hour == 0 || hour == 12) ? "12" : hour%12 + "";
        if(minute != 0){
            toReturn = toReturn + "." + minute;
        }
        String meridiem = meridiemFlag ? "pm" : "am";
        return toReturn + meridiem;
    }
}
