/**
 * Represents the time described in events
 *
 */
public class DateTime {

    private int hour;
    private int minute; 
    private int date;
    private String month;
    private int year;

    /**
     * Constructor for the DateTime class.
     *
     * @param hour in 24 hour time format (e.g. 14)
     * @param minute e.g. 00
     * @param date e.g. 6
     * @param month in short form (e.g. Aug, Jun, Apr)
     * @param year e.g. 2019
     */

    public DateTime(int hour, int minute, int date, String month, int year){
        this.hour = hour ;
        this.minute = minute ;
        this.date = date ;
        this.month = month ;
        this.year = year;
    }

    /**
     * Returns the hour of the time
     *
     * @return int representing the hour 
     */
    public int getHour(){
        return hour;
    }

    /**
     * Returns the minute of the time
     *
     * @return int representing the minute 
     */
    public int getMin(){
        return minute;
    }

    /**
     * Returns the string representation of the DateTime class
     *
     * @return string representation
     */
    @Override 
    public String toString(){
        
        if(String.valueOf(date).contains("1")){
            String convertedDate =Integer.toString(date) + "st";
        }else if (String.valueOf(date).contains("2")){
            String convertedDate =Integer.toString(date) + "nd";
        }else if (String.valueOf(date).contains("3")){
            String convertedDate =Integer.toString(date) + "rd";
        }else{
            String convertedDate =Integer.toString(date) + "th";
        }
        
        String convertedHour;

        if(hour > 12){
            convertedHour = Integer.toString(hour - 12) + "pm";
        }else{
            convertedHour = Integer.toString(hour) + "am";
        }
        return month + " " + date + " " + convertedHour;   
        
    }

    /**
     * Returns the string representation needed for storage
     *
     * @return string format needed for storage
     */
    public String toFileString(){
        return date + " " + month + " " + year + " " +hour + " "+ minute ;
    }
}