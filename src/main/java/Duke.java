import java.io.*;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Label;
//import javafx.stage.Stage;

/**
 * Main class that runs the Duke program.
 */
public class Duke {

    /**
     * Converts a date and time string from dd/mm/yy hh:mm format to Day of Month, Year hh.mm(am/pm) format.
     *
     * @param dateTime date and time in dd/mm/yy hh:mm format.
     * @return Day of Month, Year hh.mm(am/pm) format. Example: 2nd of December 2019, 6pm.
     */
    public static String toDateString(String dateTime) {
        try {
            String result = "";
            String[] s = dateTime.split(" ");
            String[] date = s[0].split("/");
            int intDay = Integer.parseInt(date[0]);
            int intMonth = Integer.parseInt(date[1]);
            int year = Integer.parseInt(date[2]);
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
            String month = new DateFormatSymbols().getMonths()[intMonth - 1];
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
                "    ____________________________________________________________\n" +
                        "     " + message + "\n" +
                        "    ____________________________________________________________");
    }

    private Storage storage;
    private TaskList tasks = new TaskList();
    private Ui ui;

    /**
     * Constructs a new Duke object.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        try {
            storage.load(tasks);
        } catch (Exception e) {
        }

    }

    /**
     * Runs the Duke program.
     */
    public void run() {
        Parser parser = new Parser();
        print("Hello! I am Duke\n" +
                "     What can I do for you?");
        this.tasks.listTasks();
        boolean isExit = false;
        while (!isExit) {
            try {
                ui.setInput();
                String input = ui.getInput();
                Command c = Parser.parse(input);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
                storage.save(tasks);
            } catch (Exception e) {
//                System.out.println(e);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new Duke().run();
    }


}
