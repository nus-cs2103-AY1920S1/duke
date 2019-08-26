public class DateTime {

    private int hour;
    private int minute; 
    private int date;
    private String month;
    private int year;

    // Map<String, String> map = new HashMap<String, String>();
    // map.put("dog", "type of animal");
    

    public DateTime(int hour, int minute, int date, String month, int year){
        this.hour = hour ;
        this.minute = minute ;
        this.date = date ;
        this.month = month ;
        this.year = year;
    }
    
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

    public String toFileString(){
        return date + "/" + month + "/" + year + "/" +hour + "/"+ minute ;
    }
}