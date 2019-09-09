package duke.task;

//Interface housing public static final constants for testing
public interface TaskTestConstants {
    String VALID_DATE_1 = "01/01/1997 0000";
    String VALID_DATE_2 = " 01/01/1997 0003   ";
    String VALID_DATE_3 = "2/12/2019 1900";
    String VALID_DATE_4 = "8/8/2000 2000";
    String INVALID_DATE_1 = "1/Jan/1997 1200";
    String INVALID_DATE_2 = "";
    String INVALID_DATE_3 = "01/01/1997      1200";

    String VALID_TIME_1 = "0000";
    String VALID_TIME_2 = "  0120  ";
    String INVALID_TIME_1 = "01 20";
    String INVALID_TIME_2 = "130am";
    String INVALID_TIME_3 = VALID_DATE_1;

    //double check it is valid if valid date & time constants are changed
    String VALID_PERIOD_1 = "01/01/1997 1000 to 1100";
    String VALID_PERIOD_2 = "10/10/2000 0900 to 1500";
    String VALID_PERIOD_3 = "1/1/1997 1000 to 1/1/2019 0900";
    String VALID_PERIOD_4 = "9/9/2016 1200 to 25/10/2016 1200";

    //format invalid
    String INVALID_PERIOD_1 = "1-1-1997 1200 to 10/10/2000 2000";
    //timing invalid
    String INVALID_PERIOD_2 = "01/01/1997 1000 to 0900";
    String INVALID_PERIOD_3 = "02/01/1997 1000 to 1/1/1997 1100";

    String VALID_DESCRIPTION_1 = "just some description";
    String INVALID_DESCRIPTION_1 = "";
}
