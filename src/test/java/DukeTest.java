import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DukeTest {

    public static void main(String[] args) {
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        String timeDate = "01/01/2019 0900";
        LocalDateTime localDate1 = LocalDateTime.parse(timeDate, formatter1);
//        LocalDateTime localDate2 = LocalDateTime.parse(timeDate, formatter2);
        String localDate2 = localDate1.format(formatter2);
        System.out.println(localDate1);
        System.out.println(localDate2);
    }
}