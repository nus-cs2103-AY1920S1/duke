import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;

public class Parser {
    String convertStringToTime(String stringDate, String taskType) {
        String convertedTime;

        switch (taskType) {
        case "deadline":
            DateTimeFormatter deadlineInputFormatter = DateTimeFormatter.ofPattern("dd/MM/yy HHmm");
            LocalDateTime deadlineDateTime = LocalDateTime.parse(stringDate, deadlineInputFormatter);

            DateTimeFormatter deadlineOutputFormatter = DateTimeFormatter.ofPattern("yyyy MMMM dd hh:mma");
            convertedTime = deadlineDateTime.format(deadlineOutputFormatter);
            break;
        case "event":
            String[] dateTime = stringDate.split(" ");
            String eventDate = dateTime[0];
            String eventTime = dateTime[1];

            DateTimeFormatter eventDateInputFormatter = DateTimeFormatter.ofPattern("dd/MM/yy");
            DateTimeFormatter eventDateOutputFormatter = DateTimeFormatter.ofPattern("yyyy MMMM dd");
            LocalDate eventDateLDT = LocalDate.parse(eventDate, eventDateInputFormatter);
            String dateOutput = eventDateLDT.format(eventDateOutputFormatter);

            String[] timeStartEnd = eventTime.split("-");

            String startTime = timeStartEnd[0];
            String endTime = timeStartEnd[1];

            DateTimeFormatter eventTimeInputFormatter = DateTimeFormatter.ofPattern("HHmm");
            DateTimeFormatter eventTimeOutputFormatter = DateTimeFormatter.ofPattern("hh:mma");

            LocalTime startTimeLDT = LocalTime.parse(startTime, eventTimeInputFormatter);
            LocalTime endTimeLDT = LocalTime.parse(endTime, eventTimeInputFormatter);
            String startTimeOutput = startTimeLDT.format(eventTimeOutputFormatter);
            String endTimeOutput = endTimeLDT.format(eventTimeOutputFormatter);

            convertedTime = String.format("%s %s to %s", dateOutput, startTimeOutput, endTimeOutput);
            break;
        default:
            convertedTime = null;
            break;
        } 

        return convertedTime;
    }
}