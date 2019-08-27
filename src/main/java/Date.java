public class Date {
    private int day;
    private int month;
    private int year;
    private int time;

    public Date(int day, int month, int year, int time) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.time = time;
    }

    @Override
    public String toString() {
        String str = "";

        // Day
        if (day == 1) {
            str = str + "1st of ";
        } else if (day == 2) {
            str = str + "2nd of ";
        } else if (day == 3) {
            str = str + "3rd of ";
        } else {
            str = str + day + "th of ";
        }

        // Month
        switch (month) {
            case 1: str = str + "January ";
                break;
            case 2: str = str + "February ";
                break;
            case 3: str = str + "March ";
                break;
            case 4: str = str + "April ";
                break;
            case 5: str = str + "May ";
                break;
            case 6: str = str + "June ";
                break;
            case 7: str = str + "July ";
                break;
            case 8: str = str + "August ";
                break;
            case 9: str = str + "September ";
                break;
            case 10: str = str + "October ";
                break;
            case 11: str = str + "November ";
                break;
            default: str = str + "December ";
                break;
        }

        // Year
        str = str + year + ", ";

        // Time
        if (time >= 1300) {
            int minutes = time % 100;
            int PMtime = (time - 1200) / 100;
            if (minutes == 0) {
                str = str + PMtime + "pm";
            } else {
                str = str + PMtime + "." + minutes + "pm";
            }
        } else if (time < 1200){
            int minutes = time % 100;
            int AMtime = time / 100;
            if (minutes == 0) {
                str = str + AMtime + "am";
            } else {
                str = str + AMtime + "." + minutes + "am";
            }
        } else {
            int minutes = time % 100;
            int noonTime = time / 100;
            if (minutes == 0) {
                str = str + noonTime + "pm";
            } else {
                str = str + noonTime + "." + minutes + "pm";
            }
        }
        return str;
    }
}
