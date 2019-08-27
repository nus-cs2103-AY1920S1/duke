import java.lang.reflect.Array;

public class Date {
    private int day;
    private int month;
    private int year;

    public Date(String inputDate) {
        String[] tempStringArr = inputDate.split("/");
        this.day = Integer.parseInt((String)Array.get(tempStringArr, 0));
        this.month = Integer.parseInt((String)Array.get(tempStringArr, 1));
        this.year = Integer.parseInt((String)Array.get(tempStringArr, 2));
    }

    public String monthAsString() {
        if(month == 1) {
            return "January";
        } else if(month == 2) {
            return "February";
        } else if(month == 3) {
            return "March";
        } else if(month == 4) {
            return "April";
        } else if(month == 5) {
            return "May";
        } else if(month == 6) {
            return "June";
        } else if(month == 7) {
            return "July";
        } else if(month == 8) {
            return "August";
        } else if(month == 9) {
            return "September";
        } else if(month == 10) {
            return "October";
        } else if(month == 11) {
            return "November";
        } else {
            return "December";
        }
    }

    @Override
    public String toString() {
        StringBuilder outputString = new StringBuilder();
        outputString.append(this.day);
        if(this.day == 1) {
            outputString.append("st");
        } else if(this.day == 2) {
            outputString.append("nd");
        } else if(this.day == 3) {
            outputString.append("rd");
        } else {
            outputString.append("th");
        }
        outputString.append(" of ");
        outputString.append(this.monthAsString());
        outputString.append(" ");
        outputString.append(this.year);
        return outputString.toString();
    }
}
