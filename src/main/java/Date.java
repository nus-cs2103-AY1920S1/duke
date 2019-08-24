public class Date {
    private int day;
    private int month;
    private int year;
    private String dateString;
    private static String[] MONTHS = {"January", "February", "March", "April", "May", "June", "July", "August",
        "September", "October", "November", "December"};


    public Date(String dateString){
        this.dateString = dateString;
        String[] tokens = dateString.split("/");
        this.day = Integer.parseInt(tokens[0]);
        this.month = Integer.parseInt(tokens[1]);
        this.year = Integer.parseInt(tokens[2]);
    }

    public String getDateString(){
        return dateString;
    }

    @Override
    public String toString(){
        String toReturn = "";
        if(day % 10 == 2 && day != 12){
            toReturn = day + "nd";
        } else if(day % 10 == 3 && day != 13){
            toReturn = day + "rd";
        } else if(day % 10 == 1 && day != 11){
            toReturn = day + "st";
        } else {
            toReturn = day + "th";
        }
        toReturn = toReturn + " of " + MONTHS[month-1] + " " + year;
        return toReturn;
    }

}
