import java.text.DateFormatSymbols;

public class DateTime {

    private String day;
    private String month;
    private String year;
    private String time;
    private String ampm;

    public DateTime(String toDateTime){
        String[] dateAndTime = toDateTime.split(" ");
        String date = dateAndTime[1];
        String time = dateAndTime[2];
        String[] dayMonthYear = date.split("/");
        int dayInt = Integer.valueOf(dayMonthYear[0]);
        switch (dayInt){
            case 1 :
                this.day = "1st";
                break;
            case 2 :
                this.day = "2nd";
                break;
            case 3 :
                this.day = "3rd";
                break;
            default :
                this.day = dayInt + "th";
        }
        int monthInt = Integer.valueOf(dayMonthYear[1]);
        this.month = new DateFormatSymbols().getMonths()[monthInt - 1];
        this.year = dayMonthYear[2];
        int timeInt = Integer.valueOf(time);
        this.ampm = timeInt >= 1200 ? "pm" : "am";
        int minutes = timeInt % 100;
        int twentyFourHour = (timeInt - minutes)/ 100;
        int hours = twentyFourHour % 12 == 0 ? 12 : twentyFourHour % 12;
        this.time = hours + "." + String.format("%02d", minutes);
    }

    @Override
    public String toString(){
        return " " + day + " of " + month + " " + year + ", " + time + ampm;
    }
}
