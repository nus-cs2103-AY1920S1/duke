import java.io.Serializable;

public class DukeDuration implements Serializable {
    private final DukeDateTime startDateTime;
    private final DukeDateTime endDateTime;

    public DukeDuration(DukeDateTime startDateTime, DukeDateTime endDateTime) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    @Override
    public String toString() {
        if(startDateTime.isEmpty() && endDateTime.isEmpty()) {
            return "Unspecified Time";
        } else {
            StringBuilder sb = new StringBuilder();

            if(!startDateTime.isEmpty()) {
                sb.append(startDateTime.toString());
            }

            if(!endDateTime.isEmpty()) {
                sb.append(" to ");
                sb.append(endDateTime.toString());
            }
            
            return sb.toString();
        }
    }
}
