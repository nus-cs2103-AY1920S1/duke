import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;

public class Parser {
    String convertStringToTime(String stringDate, String taskType) throws DateTimeParseException {
        String convertedTime;

        switch (taskType) {
        case "deadline":
            DateTimeFormatter deadlineInputFormatter = DateTimeFormatter.ofPattern("dd/MM/yy HHmm");
            LocalDateTime deadlineDateTime = LocalDateTime.parse(stringDate, deadlineInputFormatter);

            DateTimeFormatter deadlineOutputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mma");
            convertedTime = deadlineDateTime.format(deadlineOutputFormatter);
            break;
        case "event":
            String[] dateTime = stringDate.split(" ");

            if (dateTime.length != 2) {
                throw new IncorrectDukeCommand("     The Date or Time is missing!");
            }

            String eventDate = dateTime[0];
            String eventTime = dateTime[1];

            DateTimeFormatter eventDateInputFormatter = DateTimeFormatter.ofPattern("dd/MM/yy");
            DateTimeFormatter eventDateOutputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
            LocalDate eventDateLDT = LocalDate.parse(eventDate, eventDateInputFormatter);
            String dateOutput = eventDateLDT.format(eventDateOutputFormatter);

            if (!eventTime.contains("-")) {
                String errorMessage = "     You seem to be missing the Start/End time of your event!\n"
                        + "     Please follow this format for an Event: 'dd/mm/yy hhmm-hhmm'";
                throw new IncorrectDukeCommand(errorMessage);
            }

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