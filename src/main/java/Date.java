public class Date {

    public static boolean verifyDateTimeDeadline(String dateTime) {
        System.out.println("Input: " + dateTime);
        if (dateTime.length() != 15) {
            return false;
        }
        String date = dateTime.substring(0, 10);
        String time = dateTime.substring(11, 15);
        try {
            int dd = Integer.parseInt(date.substring(0, 2));
            int mm = Integer.parseInt(date.substring(3, 5));
            int yyyy = Integer.parseInt(date.substring(6, 10));
            boolean dateCorrect = ((01 <= dd) && (dd <= 31)
                    && (01 <= mm) && (mm <= 12));
            int hh = Integer.parseInt(time.substring(0, 2));
            int minutes = Integer.parseInt(time.substring(2));
            boolean timeCorrect = ((00 <= hh) && (hh <= 23)
                    && (00 <= minutes) && (minutes <= 59));
            return dateCorrect && timeCorrect;
        } catch (NumberFormatException e) {
            System.out.println("Invalid date-time format.");
            return false;
        }
    }

    public static boolean verifyDateTimeEvent(String dateTime) {
        System.out.println("Input: " + dateTime);
        if (dateTime.length() != 20) {
            return false;
        }
        String date = dateTime.substring(0, 10);
        String time = dateTime.substring(11, 20);
        try {
            int dd = Integer.parseInt(date.substring(0, 2));
            int mm = Integer.parseInt(date.substring(3, 5));
            int yyyy = Integer.parseInt(date.substring(6, 10));
            boolean dateCorrect = ((01 <= dd) && (dd <= 31)
                    && (01 <= mm) && (mm <= 12));
            int hh1 = Integer.parseInt(time.substring(0, 2));
            int minutes1 = Integer.parseInt(time.substring(2, 4));
            int hh2 = Integer.parseInt(time.substring(5, 7));
            int minutes2 = Integer.parseInt(time.substring(7, 9));
            boolean timeCorrect = ((00 <= hh1) && (hh1 <= 23)
                    && (00 <= minutes1) && (minutes1 <= 59))
                    && ((00 <= hh2) && (hh2 <= 23)
                    && (00 <= minutes2) && (minutes2 <= 59));
            return dateCorrect && timeCorrect;
        } catch (NumberFormatException e) {
            System.out.println("Invalid date-time format.");
            return false;
        }
    }
}
