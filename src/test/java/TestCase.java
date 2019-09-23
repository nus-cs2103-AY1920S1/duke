public class TestCase {
    static String EVENT_DESCRIPTION = "Movie date";
    static String TODO_DESCRIPTION = "Wash plates";
    static String DEADLINE_DESCRIPTION = "Submit Reflections";
    static String DESCRIPTION_FALSE = "";
    static String DATE = "15/7/2019";
    static String TIME = "1800";
    static String EVENT_SUCCESSFUL_OUTPUT = "[E][N] Movie date (at: Mon, 15 Jul 2019 06:00PM)";
    static String TODO_SUCCESSFUL_OUTPUT = "[T][N] Wash plates";
    static String DEADLINE_SUCCESSFUL_OUTPUT = "[D][N] Submit Reflections (by: Mon, 15 Jul 2019 06:00PM)";
    static String EVENT_DONE_OUTPUT = "[E][Y] Movie date (at: Mon, 15 Jul 2019 06:00PM)";
    static String TODO_DONE_OUTPUT = "[T][Y] Wash plates";
    static String DEADLINE_DONE_OUTPUT = "[D][Y] Submit Reflections (by: Mon, 15 Jul 2019 06:00PM)";
    static String EVENT_UPDATE = "_______________________________________________________\n"
            + "     Got it. Your task is now updated as: \n"
            + "       [E][N] Movie date (at: Mon, 15 Jul 2019 07:30PM)\n"
            + "     Now you have 3 task(s) in the list.\n"
            + "_______________________________________________________\n";
    static String TODO_UPDATE =  "_______________________________________________________\n"
            + "     Got it. Your task is now updated as: \n"
            + "       [T][N] Wipe floor\n"
            + "     Now you have 3 task(s) in the list.\n"
            + "_______________________________________________________\n";
    static String DEADLINE_UPDATE = "_______________________________________________________\n"
            + "     Got it. Your task is now updated as: \n"
            + "       [D][N] Submit Reflections (by: Sun, 18 Aug 2019 06:00PM)\n"
            + "     Now you have 3 task(s) in the list.\n"
            + "_______________________________________________________\n";
    static String NEW_TODO_DESCRIPTION = "Wipe floor";
    static String NEW_DATE = "18/8/2019";
    static String NEW_TIME = "1930";

}
