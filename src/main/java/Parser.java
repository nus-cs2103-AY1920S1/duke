import java.util.Scanner;

public class Parser {

    Scanner sc = new Scanner(System.in);

    public static Command parse(String line) {

    }


    public static String dateFormatter(String text) {
        int num = text.indexOf("/");
        int num1 = text.indexOf(" ");
        int dayDate = Integer.parseInt(text.substring(num + 4, num + 6));
        int monthDate = Integer.parseInt(text.substring(num + 7, num + 9));
        int timeHour = Integer.parseInt(text.substring(num + 15, num + 17));
        int timeMin = Integer.parseInt(text.substring(num + 17));
        if (dayDate > 0 && dayDate <= 31 && monthDate > 0 && monthDate <= 12
                && timeHour > 0 && timeHour <= 24 && timeMin >= 0 && timeMin <= 59) {
            Task task = new Deadline(text.substring(num1, num - 1),
                    text.substring(num + 4));
            String taskers = task.toString();
        return taskers;
    }
}
