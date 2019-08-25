import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class DukeDateTime implements Serializable {
    private final LocalDate date;
    private final LocalTime time;

    public DukeDateTime(LocalDate date, LocalTime time) {
        this.date = date;
        this.time = time;
    }

    public boolean isEmpty() {
        return date == null && time == null;
    }

    @Override
    public String toString() {
        if(date == null && time == null) {
            return "Unspecified Time";
        } else {
            StringBuilder sb = new StringBuilder();
            boolean dateExists = false;

            if(date != null) {
                String dateString = date.toString();
                sb.append(dateString.substring(8, 10));
                sb.append("/");
                sb.append(dateString.substring(5, 7));
                sb.append("/");
                sb.append(dateString.substring(0, 4));

                dateExists = true;
            }

            if(time != null) {
                if(dateExists) {
                    sb.append(" ");
                }

                String timeString = time.toString();
                sb.append(timeString.substring(0, 2));
                sb.append(timeString.substring(3, 5));
            }

            return sb.toString();
        }
    }
}
