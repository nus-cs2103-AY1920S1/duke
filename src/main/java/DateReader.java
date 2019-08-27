public class DateReader {
    protected String date;

    public DateReader(String date) {
        this.date = date;
    }



    public static String readDate(DateReader dr) {
    	String[] arr = dr.date.split("/");

    	String dd = arr[0];
    	String mm = arr[1];
    	String yy = arr[2].split(" ")[0];
        String time = arr[2].split(" ")[1];

    	switch (dd) {
    	case "1":
    		dd = "1st";
    		break;
    	case "2":
    		dd = "2nd";
    		break;
    	case "3":
    		dd = "3rd";
    		break;
        case "21":
            dd += "st";
            break;
        case "22":
            dd += "nd";
            break;
        case "23":
            dd += "rd";
            break;
        case "31":
            dd += "st";
            break;
        default:
            dd += "th";
            break;
    	}

    	switch (mm) {
    	case "1":
    		mm = "January";
    		break;
    	case "2":
    		mm = "February";
    		break;
    	case "3":
    		mm = "March";
    		break;
    	case "4":
    		mm = "April";
    		break;
    	case "5":
    		mm = "May";
    		break;
    	case "6":
    		mm = "June";
    		break;
    	case "7":
    		mm = "July";
    		break;
    	case "8":
    		mm = "August";
    		break;
    	case "9":
    		mm = "September";
    		break;
    	case "10":
    		mm = "October";
    		break;
    	case "11":
    		mm = "November";
    		break;
    	case "12":
    		mm = "December";
    		break;
    	
    	}

        int newtime;
        if (time.equals("1200") || time.equals("0000")) {
            newtime = 12;
        } else {
            newtime = (Integer.parseInt(time)/100)%12;
        }

        String dateandtime = dd + " of " + mm + " " + yy + " " + newtime;

        if (!time.substring(2).equals("00")) {
            dateandtime += time.substring(2);
        } else {}

        if (Integer.parseInt(time) >= 1200) {
            dateandtime += "pm";
        } else {
            dateandtime += "am";
        }
    	return dateandtime;

    }
}
