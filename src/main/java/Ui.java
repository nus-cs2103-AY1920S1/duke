import java.text.DateFormatSymbols;
import java.util.Scanner;

/**
 * User interaction class to take care of all user interactions in the program.
 */
public class Ui {
    private String input = "";
    private String[] inputArr;
    private String response;
    private Scanner sc = new Scanner(System.in);

    /**
     * Converts a date and time string from dd/mm/yy hh:mm format to Day of Month, Year hh.mm(am/pm) format.
     *
     * @param dateTime date and time in dd/mm/yy hh:mm format.
     * @return Day of Month, Year hh.mm(am/pm) format. Example: 2nd of December 2019, 6pm.
     */
    static String toDateString(String dateTime) {
        try {
            String[] s = dateTime.split(" ");
            String[] date = s[0].split("/");
            int intDay = Integer.parseInt(date[0]);
            String day = "";
            int intTime = Integer.parseInt(s[1]);
            String time = "";
            if (intDay > 3) {
                day = intDay + "th";
            } else if (intDay == 1) {
                day = intDay + "st";
            } else if (intDay == 2) {
                day = intDay + "nd";
            } else if (intDay == 3) {
                day = intDay + "rd";
            }
            int intMinutes = intTime % 100;
            String minutes = "";
            if (intMinutes / 10 == 0) {
                minutes = "0" + intMinutes;
            }
            if (intTime > 1259) {
                intTime -= 1200;
                time = intTime / 100 + "." + minutes + "pm";
            } else if (intTime > 1159) {
                time = intTime / 100 + "." + minutes + "pm";
            } else if (intTime < 1159) {
                time = intTime / 100 + "." + minutes + "am";
            }
            int intMonth = Integer.parseInt(date[1]);
            String month = new DateFormatSymbols().getMonths()[intMonth - 1];
            int year = Integer.parseInt(date[2]);
            String result = "";
            result = day + " of " + month + " " + year + ", " + time;
            return result;
        } catch (Exception e) {
            return dateTime;
        }
    }

    /**
     * Prints message wrapped in "_______".
     *
     * @param message message before formatting with "_____".
     */
    public static void print(String message) {
        System.out.println(
                "    ____________________________________________________________\n"
                        +
                        "     " + message + "\n"
                        +
                        "    ____________________________________________________________");
    }

    /**
     * Sets the input from the user.
     */
    void setInput() {
        String newInput = sc.nextLine();
        this.input = newInput;
        this.inputArr = newInput.split(" ");
    }

    /**
     * Returns input from the user.
     *
     * @return input from the user.
     */
    public String getInput() {
        return this.input;
    }

    /**
     * Returns input of user as a String array.
     *
     * @return input of user as a String array.
     */
    public String[] getInputArr() {
        return this.inputArr;
    }

    /**
     * Returns description of Todo task.
     *
     * @return description of Todo task.
     */

    void setResponse(String response) {
        this.response = "    ______________________________________________\n"
                +
                "     " + response + "\n"
                +
                "    ______________________________________________";
    }
    void printResponse(){
        print(response);
    }
    public String getResponse(){
        return this.response;
    }

}
