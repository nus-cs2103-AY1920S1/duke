package duke;

public class ConvertDateTime {

    public ConvertDateTime(){

    }

    public String Convert(String timeframe){

        int in = timeframe.indexOf('/');                           //find first instance of '/'
        int day = Integer.parseInt(timeframe.substring(0, in));    //sift out day
        String sub1 = timeframe.substring(in+1);                   //substring: month onwards
        int in2 = sub1.indexOf('/');
        int month =  Integer.parseInt(sub1.substring(0, in2));     //sift out month
        String sub2 = sub1.substring(in2+1);                       //substring: year onwards
        String year = sub2.substring(0, 4);                        //sift out year
        String time = sub2.substring(5);                           //sift out time (24hr clock format)

        String converted_day;
        switch (day){
            case 1:
            case 21:
            case 31:
                converted_day = Integer.toString(day) + "st";
                break;
            case 2:
            case 22:
                converted_day = Integer.toString(day) + "nd";
                break;
            case 3:
            case 33:
                converted_day = Integer.toString(day) + "rd";
                break;
            default:
                converted_day = Integer.toString(day) + "th";
                break;
        }

        String converted_month;
        switch (month){
            case 1:
                converted_month = "January"; break;
            case 2:
                converted_month = "February"; break;
            case 3:
                converted_month = "March"; break;
            case 4:
                converted_month = "April"; break;
            case 5:
                converted_month = "May"; break;
            case 6:
                converted_month = "June"; break;
            case 7:
                converted_month = "July"; break;
            case 8:
                converted_month = "August"; break;
            case 9:
                converted_month = "September"; break;
            case 10:
                converted_month = "October"; break;
            case 11:
                converted_month = "November"; break;
            case 12:
                converted_month = "December"; break;
            default:
                converted_month = ""; break;
        }

        String period;                                 //indicate AM or PM
        int hour = Integer.parseInt(time.substring(0,2));
        if(hour>=12)
            period = "pm";
        else
            period = "am";

        String converted_hr;
        if((Integer.parseInt(time.substring(0,2))) > 12) {
            converted_hr = Integer.toString((Integer.parseInt(time.substring(0, 2))) - 12);
        }
        else{
            converted_hr = Integer.toString((Integer.parseInt(time.substring(0, 2))));
        }

        String converted_min;
        int min = Integer.parseInt(time.substring(2));
        if(min>0)
            converted_min = ":" + time.substring(2);
        else
            converted_min = "";

        return converted_day + " of " + converted_month + " " + year + ", " + converted_hr + converted_min + period ;
    }

}
