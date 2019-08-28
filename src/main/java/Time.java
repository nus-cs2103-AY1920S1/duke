public class Time {
    private int hour;
    private int minute;
    private String period;

    public Time(String time){
        int tempTime = Integer.parseInt(time);
        if(tempTime > 1200) {
            period = "pm";
            tempTime -= 1200;
        }else
            period = "am";
        this.hour = tempTime/100;
        this.minute = tempTime%100;
    }

    public String toString(){
        if(this.minute == 0)
            return this.hour + period;
        else
            return this.hour + "." + this.minute + period;
    }
}
