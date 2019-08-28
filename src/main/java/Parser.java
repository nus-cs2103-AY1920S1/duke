import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Parser {
    private String input;

    public Parser(String input) {
        this.input = input;
    }

    public TaskType getInputTaskType() {
        return TaskType.convertToTaskType(this.input.split("\\s+")[0]);
    }
    public String getInputEntireDescription() {
        Scanner sc = new Scanner(this.input);
        sc.next(); //scan past taskType
        String description = sc.nextLine().trim();
        sc.close();
        return description;
    }

    public String getDescriptionWithoutDate() { //only if input is entire description alr; aka for indiv Task classes
        return "";
    }

    public Date convertToDate() {
        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy HHmm").parse(this.input);
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            return date;
        }
    }
}
