import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Parser {
    public static Command parse(String input) {

    }

    /**
     * Processes Input Event/Deadline to Description & Details
     * @param input Task String (without command)
     * @return Array. Index 0 = Description. Index 1  = Details
     * @throws ArrayIndexOutOfBoundsException Command missing details
     */
    public static String[] process(String input) throws ArrayIndexOutOfBoundsException {
        String[] desc = input.split("/");
        String[] temp = desc[1].split(" ");
        desc[1] = desc[1].replace(temp[0], "");
        return desc;
    }

    public static Date getDate(String details) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HHmm");
        return format.parse(details);
    }
}
