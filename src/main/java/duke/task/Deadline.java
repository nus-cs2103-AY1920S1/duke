package duke.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import duke.execution.DukeException;
/**
 * Represents Deadline object, subclass of Task
 */
public class Deadline extends Task {
    private LocalDateTime deadline; //   DD/MM/YYYY

    public Deadline(String description, String deadline) throws DukeException{
        super(description);
        this.setDateTime(deadline);
    }

    /**
     * Sets local datetime specified by user input
     * @param deadline user input
     * @throws DukeException thrown if user input does not follow format
     */
    private void setDateTime(String deadline) throws DukeException{
        String datetimeInput[] = deadline.split(" ");
        String dateInput[] = datetimeInput[0].split("/");
        String timeInput = datetimeInput[1];
        int year, month, day, hour, minute;
        year = Integer.parseInt(dateInput[2]);
        month = Integer.parseInt(dateInput[1]);
        day = Integer.parseInt(dateInput[0]);
        hour = Integer.parseInt(timeInput)/100;
        minute = Integer.parseInt(timeInput)%100;
        try {
            this.deadline = LocalDateTime.of(year, month, day, hour, minute);
        }catch(DateTimeException e){
            throw new DukeException("Bad date or time provided");
        }catch(NumberFormatException e){
            throw new DukeException("Bad date or time format");
        }
    }

    public Task updateDateTime(String newDateTime) throws DukeException{
        this.setDateTime(newDateTime);
        return this;
    }

    public String toFileString(){
        StringBuilder fileString = new StringBuilder();
        fileString.append("D | 0 | " + description + " | ");
        fileString.append(datetimeToString() + "\n");
        return fileString.toString();
    }

    /**
     * Helper function to return local datetime in a specific format
     * @return local datetime
     */
    private String datetimeToString(){
        StringBuilder datetimeString = new StringBuilder();
        datetimeString.append(deadline.getDayOfMonth() + "/" + deadline.getMonthValue() + "/" + deadline.getYear());
        if(deadline.getHour()<10) {
            datetimeString.append(" 0" + deadline.getHour());
        }else{
            datetimeString.append(" " + deadline.getHour());
        }
        if(deadline.getMinute()<10){
            datetimeString.append("0" + deadline.getMinute());
        }else{
            datetimeString.append(deadline.getMinute());
        }
        return datetimeString.toString();
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[D]" + "[" + "\u2713" + "]" + this.description + " (by: " + this.datetimeToString() + ")";
        } else {
            return "[D]" + "[" + "\u2718" + "]" + this.description + " (by: " +this.datetimeToString() + ")";
        }
    }
}